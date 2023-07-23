//
//  ViewUtil.swift
//  iosApp
//
//  Created by MAC on 21/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit

extension UIView {
    
    public class func instantiateFromNib<T: UIView>(viewType: T.Type) -> T {
        return (Bundle.main.loadNibNamed(String(describing: viewType), owner: nil, options: nil)?.first as? T) ?? T()
    }

    public class func instantiateFromNib() -> Self {
        return instantiateFromNib(viewType: self)
    }
    
}
