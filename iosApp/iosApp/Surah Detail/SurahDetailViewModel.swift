//
//  SurahDetailViewModel.swift
//  iosApp
//
//  Created by MAC on 17/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

extension SurahDetailView {
    @MainActor class SurahDetailViewModel: ObservableObject {
        private var quranRepository: QuranRepository? = nil
        
        @Published var listAyah = [ReadSurahEn.Ayah]()

        init(quranRepository: QuranRepository? = nil) {
            self.quranRepository = quranRepository
        }
        
        func setRepostiroy(quranRepository: QuranRepository) {
            self.quranRepository = quranRepository
        }

        func getSurahById(surahId: Int32) {
            Task {
                do {
                    let data = try await quranRepository?.getReadSurahEn(surahId: surahId) ?? nil
                    self.listAyah = data?.ayah ?? []
                } catch let ex {
                    self.listAyah = []
                }
            }
        }
        
    }

}
