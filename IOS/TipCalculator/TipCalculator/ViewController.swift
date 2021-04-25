//
//  ViewController.swift
//  TipCalculator
//
//  Created by Wael Alghamdi on 1/14/2020.
//  Copyright © 2020 nsu. All rights reserved.
//

import UIKit

//Global Containers (Keys)
let SPLITS_KEY = "Splits_Key"
let TIP_KEY = "Tip_Key"

class ViewController: UIViewController {
    
    //Containers
    let defaults = UserDefaults.standard
    let fmt = NumberFormatter()
    
    var tipPercentage = 0
    var splits = 1
    var resultTotalTip = 0.0
    var resultTotalAmount = 0.0
    var resultTipEach = 0.0
    var resultTotalEach = 0.0
    
    @IBOutlet weak var checkAmountOutlet: UITextField!
    @IBOutlet weak var percentLabel: UILabel!
    @IBOutlet weak var totalTipOutlet: UITextField!
    @IBOutlet weak var totalAmountOutlet: UITextField!
    @IBOutlet weak var tipEachOutlet: UITextField!
    @IBOutlet weak var totalEachOutlet: UITextField!
    @IBOutlet weak var splitsSegementedOutlet: UISegmentedControl!
    @IBOutlet weak var tipSliderOutlet: UISlider!
    
    //Element Functions
    @IBAction func percentSlider(_ sender: UISlider) {
        percentLabel.text = "\(Int(sender.value))%"
        tipPercentage = Int(sender.value)
        performCalculations()
        updateUI()
    }
    @IBAction func splitsSegmentedControl(_ sender: UISegmentedControl) {
        splits = sender.selectedSegmentIndex + 1
        performCalculations()
        updateUI()
    }
    @IBAction func calculateButton(_ sender: UIButton) {
        checkAmountOutlet.resignFirstResponder()
        performCalculations()
        updateUI()
    }
    @IBAction func resetButton(_ sender: UIButton) {
        percentLabel.text = "\(defaults.integer(forKey: TIP_KEY))%"
        tipSliderOutlet.value = Float(defaults.integer(forKey: TIP_KEY))
        splitsSegementedOutlet.selectedSegmentIndex = defaults.integer(forKey: SPLITS_KEY)
        
        checkAmountOutlet.text = ""
        totalTipOutlet.text = ""
        totalAmountOutlet.text = ""
        tipEachOutlet.text = ""
        totalEachOutlet.text = ""
    }
    
    //Other Functions
    func performCalculations() {
        if let checkAmount = Double(checkAmountOutlet.text!) {
            //print("Check amount: \(checkAmount)")
            resultTotalTip = checkAmount * (Double(tipPercentage) / 100.0)
            resultTotalAmount = checkAmount + resultTotalTip
            resultTipEach = (resultTotalTip / Double(splits))
            resultTotalEach = ((checkAmount / Double(splits)) + resultTipEach)
        } else {
            print("There is no value to calculate.")
            resultTotalEach = 0.0
            resultTipEach = 0.0
            resultTotalAmount = 0.0
            resultTotalTip = 0.0
        }
    }
    func updateUI() {
        totalTipOutlet.text = fmt.string(for: resultTotalTip)
        totalAmountOutlet.text = fmt.string(for: resultTotalAmount)
        tipEachOutlet.text = fmt.string(for: resultTipEach)
        totalEachOutlet.text = fmt.string(for: resultTotalEach)
    }
    
    //View Loads
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // Do any additional setup after loading the view.
        fmt.numberStyle = .currency
        //fmt.locale = Locale(identifier: "ar_SA")
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        if let tipSliderDefualt = defaults.string(forKey: TIP_KEY),
        let splitControlDefault = defaults.string(forKey: SPLITS_KEY) {
            percentLabel.text = "\(tipSliderDefualt)%"
            tipPercentage = Int(tipSliderDefualt)!
            tipSliderOutlet.value = Float(tipPercentage)
            
            splitsSegementedOutlet.selectedSegmentIndex = Int(splitControlDefault)!
            splits = Int(splitControlDefault)! + 1
        } else {
            defaults.set(0, forKey: TIP_KEY)
            defaults.set(0, forKey: SPLITS_KEY)
        }
    }
}

