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
    
    var fakeData: [ReadSurah.Ayah]? = nil
    var surahId: Int32? = nil
    @StateObject var viewModel = SurahDetailViewModel(quranRepository: nil)
        
    @Environment(\.presentationMode) var presentation
    
    var body: some View {
        NavigationView {
            ZStack {
                VStack {
                    TopNavigationBar(presentation: presentation, surah: viewModel.surah)
                    ListAyah(listAyah: fakeData ?? viewModel.surah?.ayah ?? [])
                        .padding(.top, 16)
                }
            }
        }.onAppear {
            if(fakeData == nil) {
                let remote = RemoteDataSourceImpl(quranApi: QuranApi())
                let local = DatabaseModule().noteDataSource
                let repository = QuranRepositoryImpl(remote: remote, local: local)
                viewModel.setRepostiroy(quranRepository: repository)
                viewModel.getSurahById(surahId: self.surahId ?? 0)
            }
        }
        .environment(\.colorScheme, .dark)

    }
}

struct TopNavigationBar: View {
    var presentation: Binding<PresentationMode>? = nil
    var surah: ReadSurah? = nil
    
    var body: some View {

        HStack {
            Button(action: {
                self.presentation?.wrappedValue.dismiss()
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
                Text(surah?.englishName ?? "")
                    .foregroundColor(.white)
                Text("\(surah?.englishNameTranslation ?? "") (\(surah?.ayah.count ?? 0) Verse)")
                    .foregroundColor(.white)
            }
            Spacer()
        }
        .padding(.top, 28)
    }
}

struct ListAyah: View {
    let listAyah: [ReadSurah.Ayah]

    var body: some View {
        List {
            ForEach(listAyah, id: \.self.number){ ayah in
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
                        .padding(.trailing, 16)
                        Spacer()
                        Text(ayah.text)
                            .font(.custom("Amiri", size: 26))
                            .foregroundColor(.white)
                    }.padding(.bottom, 8)
                    Text(ayah.textEn)
                        .lineSpacing(8)
                        .foregroundColor(Color("Purple"))
                        .font(.system(size: 15))
                }
            }
            .listRowBackground(Color.black)
        }
    }
}

struct SurahDetailView_Previews: PreviewProvider {
    static var previews: some View {
        let list = [ReadSurah.Ayah(
            number: 1, text: "صِرَٰطَ ٱلَّذِينَ أَنۡعَمۡتَ عَلَيۡهِمۡ غَيۡرِ ٱلۡمَغۡضُوبِ عَلَيۡهِمۡ وَلَا ٱلضَّآلِّينَ", textEn: "Bismillah hirohman nirohim", numberInSurah: 1
        ),
        ReadSurah.Ayah(
            number: 2, text: "صِرَٰطَ ٱلَّذِينَ أَنۡعَمۡتَ عَلَيۡهِمۡ غَيۡرِ ٱلۡمَغۡضُوبِ عَلَيۡهِمۡ وَلَا ٱلضَّآلِّينَ صِرَٰطَ ٱلَّذِينَ أَنۡعَمۡتَ عَلَيۡهِمۡ غَيۡرِ ٱلۡمَغۡضُوبِ عَلَيۡهِمۡ وَلَا ٱلضَّآلِّينَ", textEn: "In their hearts is disease, and so God lets their disease increase; and grievous suffering awaits them because of their persistent lying.", numberInSurah: 2
        )]
        SurahDetailView(fakeData: list)
    }
}
