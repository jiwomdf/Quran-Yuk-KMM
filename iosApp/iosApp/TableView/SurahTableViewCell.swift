//
//  SurahTableView.swift
//  iosApp
//
//  Created by MAC on 05/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit
import shared

class SurahTableViewCell: UITableViewCell {
    
    
    @IBOutlet weak var surahNumberLabel: UILabel!
    @IBOutlet weak var surahNameLabel: UILabel!
    @IBOutlet weak var surahDscLabel: UILabel!
    
    func setCell(data: Surah){
        surahNumberLabel.text = String(data.number)
        surahNameLabel.text = data.englishName
        surahDscLabel.text = data.englishNameTranslation
    }
    
    
}
