//
//  SurahDetailView.swift
//  iosApp
//
//  Created by MAC on 14/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI
import shared

let EMPTY_LAST_READ_AYAH_ID: Int32 = -100

struct SurahDetailView: View {
    
    var fakeData: [ReadSurah.Ayah]? = nil
    var surahId: Int32? = nil
    var ayahId: Int32? = nil
    @State private var lastRead: LastRead? = nil
    @StateObject var viewModel = SurahDetailViewModel(quranRepository: nil)
    @State private var showAlert = false

    @Environment(\.presentationMode) var presentation
    
    var body: some View {
        ComposeView()
    }
    
    //TODO: JIWO
    /* var body: some View {
        NavigationView {
            ZStack {
                VStack {
                    TopNavigationBar(presentation: presentation, surah: viewModel.surah)
                    if(fakeData == nil && viewModel.surah?.ayah == nil) {
                        Spacer()
                        ProgressView()
                        Spacer()
                    } else {
                        let list = fakeData ?? viewModel.surah?.ayah ?? []
                        ListAyah(
                            listAyah: list,
                            ayahId: self.ayahId ?? EMPTY_LAST_READ_AYAH_ID,
                            onSaveLastRead: { ayah in
                                self.lastRead = LastRead(
                                    ayahId: ayah.numberInSurah,
                                    surahId: surahId ?? 0,
                                    surahName: viewModel.surah?.englishName ?? ""
                                )
                                viewModel.insertLastRead(lastRead: self.lastRead!)
                                showAlert.toggle()
                                DispatchQueue.main.asyncAfter(deadline: .now() + 3.0) {
                                    showAlert.toggle()
                                }
                            }
                        )
                        .padding(.top, 16)
                        
                        SnackBar(message: "Last Read is \(String(lastRead?.surahId ?? 0)):\(String(lastRead?.ayahId ?? 0))", isVisible: showAlert)
                    }
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
    } */
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
    let ayahId: Int32
    let onSaveLastRead: (ReadSurah.Ayah) -> ()
    
    var body: some View {
        ScrollViewReader { scrollView in
            ScrollView(.vertical) {
                LazyVStack {
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
                            }
                            .padding(.bottom, 8)
                            .padding(.top, 8)
                            
                            Text(ayah.textEn)
                                .lineSpacing(8)
                                .foregroundColor(Color("Purple"))
                                .font(.system(size: 15))
                        }
                        .onTapGesture(count: 2) {
                            onSaveLastRead(ayah)
                        }
                        .id(ayah.numberInSurah)
                    }
                    .listRowBackground(Color.black)
                }.onAppear {
                    if(ayahId != EMPTY_LAST_READ_AYAH_ID) {
                        scrollView.scrollTo(ayahId)
                    }
                }
            }
        }
        .padding(.leading, 20)
        .padding(.trailing, 20)
    }
}

struct SnackBar: View {
    let message: String
    let isVisible: Bool
    var body: some View {
        if isVisible {
            ZStack {
                VStack{
                    HStack {
                        Text(message)
                            .font(.footnote)
                            .foregroundColor(.black)
                            .padding(.leading, 10)
                        Spacer ()
                    }
                    .frame(height: 40)
                    .background(Color.white)
                    .cornerRadius(5)
                    .shadow(radius: 5)
                    .padding(.leading, 10)
                    .padding(.trailing, 10)
                }
            }
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
