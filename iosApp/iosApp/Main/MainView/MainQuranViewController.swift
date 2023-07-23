//
//  MainQuranViewController.swift
//  iosApp
//
//  Created by MAC on 03/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import UIKit
import shared
import MaterialComponents.MaterialSnackbar
import SwiftUI

final class MainQuranViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    var listSurah: [Surah]? = nil
    private lazy var lastReadView = { LastReadView() }()
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var lastReadCard: UIView!
    
    private lazy var viewModel: MainQuranViewModel = {
        let remote = RemoteDataSourceImpl(quranApi: QuranApi())
        let local = DatabaseModule().noteDataSource
        let vm = MainQuranViewModel(quranRepository: QuranRepositoryImpl(remote: remote, local: local))
        vm.didSucceedGetAllSurah = reloadViews
        vm.didFailedGetAllSurah = showErrorMessage
        vm.didSucceedGetLastRead = getLastRead
        vm.didFailedGetLastRead = showEmptyLastRead
        vm.didSearchSurah = reloadViews
        return vm
    }()
    
    @IBAction func seatchEditingChanged(_ sender: SearchTextField) {
        viewModel.searchSurah(rawKeyWord: sender.text ?? "")
    }
    
    @IBAction func onLastReadDidTap(_ sender: UIButton) {
        let displayView = UIHostingController(rootView: SurahDetailView(surahId: 1))

        self.navigationController?.pushViewController(displayView, animated: true)
    }
   
    override func viewDidLoad() {
        self.navigationController?.navigationBar.isHidden = true
        tableView.dataSource = self
        tableView.delegate = self
        viewModel.getAllSurah()
        viewModel.getLastRead()
        
        lastReadCard.layer.cornerRadius = 8
        lastReadCard.layer.masksToBounds = true
        lastReadCard.addSubview(lastReadView)
    }
    
    private func reloadViews(data: [Surah]?) {
        listSurah = data
        tableView.reloadData()
    }
    
    private func getLastRead(lastRead: LastRead?) {
        let displayView = UIHostingController(rootView: SurahDetailView(surahId: lastRead?.surahId))
        self.navigationController?.pushViewController(displayView, animated: true)
    }
    
    private func showEmptyLastRead(errMsg: String) {
        lastReadView.lastReadSurahNameLabel.text = "You’ve not read yet"
        lastReadView.lastReadDetailLabel.text = ""
    }
    
    private func showErrorMessage(error: String) {
        let message = MDCSnackbarMessage(text: "Something went wrong")
        MDCSnackbarManager.default.show(message)
    }
    
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return listSurah?.count ?? 0
    }
    
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: Constans.SURAH_CELL_ID, for: indexPath) as! SurahTableViewCell
        let surah = self.listSurah?[indexPath.row]
        if(surah != nil) {
            cell.setCell(data: surah!)
        }
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let surah = Int32(listSurah?[indexPath.row].number ?? 0)
        let displayView = UIHostingController(rootView: SurahDetailView(surahId: surah))

        self.navigationController?.pushViewController(displayView, animated: true)
    }

    

}