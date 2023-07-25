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

    var temptSurahs: [Surah]? = nil
    var didSearchSurah: (([Surah]?) -> Void)?
    var didSucceedGetAllSurah: (([Surah]?) -> Void)?
    var didFailedGetAllSurah: ((String) -> Void)?
    
    var didSucceedGetLastRead: ((LastRead?) -> Void)?
    var didFailedGetLastRead: ((String) -> Void)?
    
    init(quranRepository: QuranRepository? = nil) {
        self.quranRepository = quranRepository
    }

    func getAllSurah() {
        Task {
            do {
                let result = try await quranRepository?.getAllSurah() ?? nil
                temptSurahs = result
                didSucceedGetAllSurah?(result)
            } catch let ex {
                didFailedGetAllSurah?(ex.localizedDescription)
            }
        }
    }
    
    func getLastRead() {
        Task {
            do {
                let result = try await quranRepository?.getLastRead() ?? nil
                didSucceedGetLastRead?(result)
            } catch let ex {
                didFailedGetLastRead?(ex.localizedDescription)
            }
        }
    }
    
    func searchSurah(rawKeyWord: String) {
        var result: [Surah]? = nil
        if rawKeyWord.isEmpty {
            result = temptSurahs
        } else {
            let keyword = rawKeyWord.lowercased()
            result = temptSurahs?.filter {
                let name = $0.englishName.lowercased().replacingOccurrences(of: "-", with: " ")
                return name.contains(keyword)
                
            }
        }
        didSearchSurah?(result)
    }
}
