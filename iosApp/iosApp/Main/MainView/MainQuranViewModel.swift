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
    var didSearchSurah: ((SurahsState?) -> Void)?
    var didGetAllSurah: ((SurahsState?) -> Void)?
    var didGetLastRead: ((LastReadState?) -> Void)?
    
    init(quranRepository: QuranRepository? = nil) {
        self.quranRepository = quranRepository
    }

    func getAllSurah() {
        Task {
            do {
                let result = try await quranRepository?.getAllSurah() ?? nil
                temptSurahs = result
                didGetAllSurah?(SurahsState.result(result))
            } catch let ex {
                didGetAllSurah?(SurahsState.error(ex.localizedDescription))
            }
        }
    }
    
    func getLastRead() {
        Task {
            do {
                let result = try await quranRepository?.getLastRead() ?? nil
                didGetLastRead?(LastReadState.result(result))
            } catch let ex {
                didGetLastRead?(LastReadState.error(ex.localizedDescription))
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
        didSearchSurah?(SurahsState.result(result))
    }
}

enum SurahsState {
    case loading
    case result([Surah]?)
    case error(String)
}

enum LastReadState {
    case loading
    case result(LastRead?)
    case error(String)
}
