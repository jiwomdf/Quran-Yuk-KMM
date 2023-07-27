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
        vm.didGetAllSurah = didGetAllSurah
        vm.didGetLastRead = didGetLastRead
        vm.didSearchSurah = didGetAllSurah
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
    
    private func didGetAllSurah(state: SurahsState?) {
        switch state {
        case .loading:
            showIndicator(isHidden: true)
            break
        case .result(let data):
            showIndicator(isHidden: false)
            listSurah = data
            tableView.reloadData()
            break
        case .error(let err):
            showIndicator(isHidden: false)
            showErrorMessage(error: err)
            break
        case .none:
            showIndicator(isHidden: false)
            showErrorMessage(error: "Something went wrong")
            break
        }
    }
    
    private func didGetLastRead(state: LastReadState?) {
        switch state {
        case .loading:
            break
        case .result(let data):
            self.lastRead = data
            let surahId = String(lastRead?.surahId ?? 0)
            let ayahId = String(lastRead?.ayahId ?? 0)
            lastReadView.lastReadSurahNameLabel.text = lastRead?.surahName ?? ""
            lastReadView.lastReadDetailLabel.text = "\(surahId):\(ayahId)"
            break
        case .error(let err):
            showEmptyLastRead(errMsg: err)
            break
        case .none:
            showErrorMessage(error: "Something went wrong")
            break
        }
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
