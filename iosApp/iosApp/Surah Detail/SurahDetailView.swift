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
        
    @Environment(\.presentationMode) var presentation
    
    var body: some View {
        NavigationView {
            ZStack {
                VStack {
                    HStack {
                        Button(action: {
                            self.presentation.wrappedValue.dismiss()
                        }) {
                            Image("back_purple")
                                .renderingMode(.original)
                                .resizable()
                                .aspectRatio(contentMode: .fit)
                                .frame(width: 26, height: 26)
                                .padding(.trailing, 12)
                                .padding(.leading, 20)
                                .foregroundColor(.white)
                        }
                        
                        VStack(alignment: .leading) {
                            Text("Al-Fatihah")
                                .foregroundColor(.white)
                            Text("The Opener (7 Verse)")
                                .foregroundColor(.white)
                        }
                        Spacer()
                    }
                    .padding(.top, 28)
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
                                    }
                                    .padding(.top, 16)
                                    .padding(.trailing, 16)
                                    Spacer()
                                    Text(ayah.text)
                                        .font(.custom("Amiri", size: 26))
                                        .foregroundColor(.white)
                                }
                                Text(ayah.textEn)
                                    .lineSpacing(50)
                                    .foregroundColor(Color("Purple"))
                                    .font(.system(size: 15))
                                    .padding(.top, 8)
                            }
                        }
                        .listRowBackground(Color.black)
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
        .environment(\.colorScheme, .dark)

    }
}

struct SurahDetailView_Previews: PreviewProvider {
    static var previews: some View {
        SurahDetailView()
    }
}
