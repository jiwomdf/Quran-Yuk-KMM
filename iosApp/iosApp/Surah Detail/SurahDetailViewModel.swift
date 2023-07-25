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
        
        @Published var surah: ReadSurah? = nil

        init(quranRepository: QuranRepository? = nil) {
            self.quranRepository = quranRepository
        }
        
        func setRepostiroy(quranRepository: QuranRepository) {
            self.quranRepository = quranRepository
        }

        func getSurahById(surahId: Int32) {
            Task {
                do {
                    self.surah = try await quranRepository?.getReadSurah(surahId: surahId) ?? nil                
                } catch let ex {
                    self.surah = nil
                }
            }
        }
        
        func insertLastRead(lastRead: LastRead) {
            Task {
                do {
                    try await quranRepository?.insertLastRead(
                        ayahId: lastRead.ayahId,
                        surahId: lastRead.surahId,
                        surahName: lastRead.surahName
                    ) ?? nil
                } catch let ex {
                    self.surah = nil
                }
            }
        }
        
    }

}
