//
//  LastReadView.swift
//  iosApp
//
//  Created by MAC on 23/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit

class LastReadView: UIView {

    
    @IBOutlet weak var lastReadSurahNameLabel: UILabel!
    @IBOutlet weak var lastReadDetailLabel: UILabel!
    
    //MARK:
    override init(frame: CGRect) {
        super.init(frame: frame)
        
        loadViewFromNib()
    }
    
    required init?(coder aDecoder: NSCoder) {
        super.init(coder: aDecoder)
        
        loadViewFromNib()
    }
    
    //MARK:
    private func loadViewFromNib() {
        let bundle = Bundle.init(for: LastReadView.self)
        if let viewToAdd = bundle.loadNibNamed("LastReadView", owner: self, options: nil), let
            contentView = viewToAdd.first as? UIView {
            addSubview(contentView)
            contentView.frame = self.bounds
            contentView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        }
    }
    
}
