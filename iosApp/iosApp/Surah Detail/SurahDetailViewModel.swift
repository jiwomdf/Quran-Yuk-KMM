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
        
        @Published var listAyah = [ReadSurah.Ayah]()

        init(quranRepository: QuranRepository? = nil) {
            self.quranRepository = quranRepository
        }
        
        func setRepostiroy(quranRepository: QuranRepository) {
            self.quranRepository = quranRepository
        }

        func getSurahById(surahId: Int32) {
            Task {
                do {
                    print("jiwo surahId\(surahId) kotlin \(KotlinInt(value: surahId))")
                    let data = try await quranRepository?.getReadSurah(surahId: surahId) ?? nil
                    print("jiwo \(data)")
                    self.listAyah = data?.ayah ?? []
                } catch let ex {
                    self.listAyah = []
                }
            }
        }
        
        func getSurahByIdFake(surahId: Int32) {
            Task {
                do {
                    self.listAyah = [
                        ReadSurah.Ayah(
                            number: 1, text: "صِرَٰطَ ٱلَّذِينَ أَنۡعَمۡتَ عَلَيۡهِمۡ غَيۡرِ ٱلۡمَغۡضُوبِ عَلَيۡهِمۡ وَلَا ٱلضَّآلِّينَ", textEn: "Bismillah hirohman nirohim", numberInSurah: 1
                        ),
                        ReadSurah.Ayah(
                            number: 2, text: "صِرَٰطَ ٱلَّذِينَ أَنۡعَمۡتَ عَلَيۡهِمۡ غَيۡرِ ٱلۡمَغۡضُوبِ عَلَيۡهِمۡ وَلَا ٱلضَّآلِّينَ صِرَٰطَ ٱلَّذِينَ أَنۡعَمۡتَ عَلَيۡهِمۡ غَيۡرِ ٱلۡمَغۡضُوبِ عَلَيۡهِمۡ وَلَا ٱلضَّآلِّينَ", textEn: "Bismillah hirohman nirohim", numberInSurah: 2
                        )
                    ]
                } catch let ex {
                    self.listAyah = []
                }
            }
        }
        
    }

}
