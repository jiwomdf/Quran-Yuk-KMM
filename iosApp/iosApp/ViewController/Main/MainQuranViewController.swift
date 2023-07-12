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

final class MainQuranViewController: UIViewController, UITableViewDataSource, UITableViewDelegate {
    
    var listSurah: [Surah]? = nil
    
    @IBOutlet weak var tableView: UITableView!
    
    @IBAction func seatchEditingChanged(_ sender: SearchTextField) {
        viewModel.searchSurah(rawKeyWord: sender.text ?? "")
    }
    
    
    private lazy var viewModel: MainQuranViewModel = {
        let remote = RemoteDataSourceImpl(quranApi: QuranApi())
        let local = DatabaseModule().noteDataSource
        let vm = MainQuranViewModel(quranRepository: QuranRepositoryImpl(remote: remote, local: local))
        vm.didSucceedGetAllSurah = reloadViews
        vm.didFailedGetAllSurah = showErrorMessage
        vm.didSearchSurah = reloadViews
        return vm
    }()
    
    override func viewDidLoad() {
        tableView.dataSource = self
        tableView.delegate = self
        viewModel.getAllSurah()
    }
    
    
    private func reloadViews(data: [Surah]?) {
        print("jiwo data count: \(String(describing: data?.count))")
        listSurah = data
        tableView.reloadData()
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
        
    }
    
}
