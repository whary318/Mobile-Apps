//
//  SettingsViewController.swift
//  TipCalculator
//
//  Created by Wael Alghamdi on 1/14/2020.
//  Copyright © 2020 nsu. All rights reserved.
//

import UIKit

class SettingsViewController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    
    //containers
    let defaults = UserDefaults.standard
    
    var settingsSplits: [Int] = []
    var settingsTipPercentage: [Int] = []
    
    @IBOutlet weak var picker: UIPickerView!
    
    //buttons functions
    @IBAction func cancelButton(_ sender: UIButton) {
        dismiss(animated: true, completion: nil)
    }
    @IBAction func saveButton(_ sender: UIButton) {
        defaults.set(picker.selectedRow(inComponent: 0), forKey: SPLITS_KEY)
        defaults.set(picker.selectedRow(inComponent: 1), forKey: TIP_KEY)
        
        dismiss(animated: true, completion: nil)
    }
    
    //PickerView setting up
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 2
    }
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        if component == 0{
            return settingsSplits.count
        } else {
            return settingsTipPercentage.count
        }
    }
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        if component == 0 {
            return String(settingsSplits[row])
        } else {
            return String(settingsTipPercentage[row])
        }
    }
    
    //View Loads
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
        for i in 0...100 {
            settingsTipPercentage.append(i)
        }
        for i in 1...5{
            settingsSplits.append(i)
        }
    }
}
