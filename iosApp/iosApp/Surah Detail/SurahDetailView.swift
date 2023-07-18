//
//  SurahDetailView.swift
//  iosApp
//
//  Created by MAC on 14/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

struct SurahDetailView: View {
    
    var surahId: Int32? = nil
    @StateObject var viewModel = SurahDetailViewModel(quranRepository: nil)
    
    var body: some View {
        
        ZStack {
            List {
                ForEach(viewModel.listAyah, id: \.self.number){ ayah in
                    VStack(alignment: .leading){
                        HStack(alignment: .top) {
                            ZStack {
                                Text(String(ayah.numberInSurah))
                                    .zIndex(1)
                                    .foregroundColor(.white)
                                    .font(.custom("Cairo", size: 12))
                                Image("surah_star")
                                    .resizable()
                                    .scaledToFit()
                                    .frame(height: 50)
                            }.padding(.top, 16)
                            Spacer()
                            Text(ayah.text)
                                .font(.custom("Amiri", size: 26))
                        }
                        Text("Bismillah hir rahman nir raheem")
                            .lineSpacing(50)
                            .foregroundColor(.purple)
                            .font(.system(size: 14))
                            .padding(.top, 16)
                    }
                }
            }
        }.onAppear {
            let remote = RemoteDataSourceImpl(quranApi: QuranApi())
            let local = DatabaseModule().noteDataSource
            let repository = QuranRepositoryImpl(remote: remote, local: local)
            viewModel.setRepostiroy(quranRepository: repository)
            viewModel.getSurahById(surahId: self.surahId ?? 0)
        }
        
        
    }
}

struct SurahDetailView_Previews: PreviewProvider {
    static var previews: some View {
        SurahDetailView()
    }
}
