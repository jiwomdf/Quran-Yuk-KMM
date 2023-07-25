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
    
    private var listSurah: [Surah]? = nil
    private lazy var lastReadView = { LastReadView() }()
    private var lastRead: LastRead? = nil
    
    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var lastReadCard: UIView!
    @IBOutlet weak var lastReadButton: UIButton!
    @IBOutlet weak var spinnerLoading: UIActivityIndicatorView!
    
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
        if(lastRead?.ayahId == nil) {
            let message = MDCSnackbarMessage(text: "You dont have any last read")
            MDCSnackbarManager.default.show(message)
            return
        }
        
        let displayView = UIHostingController(rootView: SurahDetailView(surahId: lastRead?.surahId, ayahId: lastRead?.ayahId))
        self.navigationController?.pushViewController(displayView, animated: true)
    }
   
    override func viewDidLoad() {
        self.navigationController?.navigationBar.isHidden = true
        tableView.dataSource = self
        tableView.delegate = self
        
        showIndicator(isHidden: false)
        viewModel.getAllSurah()
        
        lastReadCard.layer.cornerRadius = 8
        lastReadCard.layer.masksToBounds = true
        lastReadCard.addSubview(lastReadView)
        
        lastReadButton.setTitle("", for: .normal)
    }
    
    override func viewDidAppear(_ animated: Bool) {
        viewModel.getLastRead()
    }
    
    private func reloadViews(data: [Surah]?) {
        showIndicator(isHidden: true)
        listSurah = data
        tableView.reloadData()
    }
    
    private func getLastRead(lastRead: LastRead?) {
        self.lastRead = lastRead
        let surahId = String(lastRead?.surahId ?? 0)
        let ayahId = String(lastRead?.ayahId ?? 0)
        
        lastReadView.lastReadSurahNameLabel.text = lastRead?.surahName ?? ""
        lastReadView.lastReadDetailLabel.text = "\(surahId):\(ayahId)"
    }
    
    private func showEmptyLastRead(errMsg: String) {
        lastReadView.lastReadSurahNameLabel.text = "You’ve not read yet"
        lastReadView.lastReadDetailLabel.text = "Press on ayah twice to save last read"
    }
    
    private func showErrorMessage(error: String) {
        showIndicator(isHidden: true)
        let message = MDCSnackbarMessage(text: "Something went wrong")
        MDCSnackbarManager.default.show(message)
    }
    
    private func showIndicator(isHidden: Bool) {
        spinnerLoading.isHidden = isHidden
        if(isHidden) {
            spinnerLoading.stopAnimating()
        } else {
            spinnerLoading.startAnimating()
        }
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
        let surahId = Int32(listSurah?[indexPath.row].number ?? 0)
        let displayView = UIHostingController(rootView: SurahDetailView(surahId: surahId))

        self.navigationController?.pushViewController(displayView, animated: true)
    }

    

}
