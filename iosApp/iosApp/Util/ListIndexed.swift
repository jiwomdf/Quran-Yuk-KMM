//
//  ListIndexed.swift
//  iosApp
//
//  Created by MAC on 17/07/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import SwiftUI

struct ListIndexed<Content: View>: View {
    let list: List<Never, Content>
    
    init<Data: MutableCollection&RandomAccessCollection, RowContent: View>(
        _ data: Binding<Data>,
        @ViewBuilder rowContent: @escaping (Data.Index, Binding<Data.Element>) -> RowContent
    ) where Content == ForEach<[(Data.Index, Data.Element)], Data.Element.ID, RowContent>,
    Data.Element : Identifiable,
    Data.Index : Hashable
    {
        list = List {
            ForEach(
                Array(zip(data.wrappedValue.indices, data.wrappedValue)),
                id: \.1.id
            ) { i, _ in
                rowContent(i, Binding(get: { data.wrappedValue[i] }, set: { data.wrappedValue[i] = $0 }))
            }
        }
    }
    
    var body: some View {
        list
    }
}
