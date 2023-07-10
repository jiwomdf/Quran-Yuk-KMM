//
//  MainQuranViewModel.swift
//  iosApp
//
//  Created by MAC on 07/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class MainQuranViewModel {
    private var quranRepository: QuranRepository? = nil

    var didSucceedGetAllSurah: (([Surah]?) -> Void)?
    var didFailedGetAllSurah: ((String) -> Void)?
    
    init(quranRepository: QuranRepository? = nil) {
        self.quranRepository = quranRepository
    }

    func getAllSurah() {
        Task {
            do {
                let result = try await quranRepository?.fetchAllSurah() ?? nil
                didSucceedGetAllSurah?(result)
            } catch let ex {
                didFailedGetAllSurah?(ex.localizedDescription)
            }
        }
    }
}
