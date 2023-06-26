package com.example.news.domain.use_case.sources

import com.example.news.R
import com.example.news.ui.common.Source
import javax.inject.Inject

class UpdateSourceStateUseCase @Inject constructor() {

    operator fun invoke(
        categorizedSources: Map<String, List<Source>>,
        key: String,
        source: Source,
        selectedVisibleOption: Int = R.string.all,
        selectedOrderOption: Int = R.string.unordered
    ) = categorizedSources.mapValues {
        if (it.key == key)
            it.value.toMutableList().apply {
                val indexOfSource = indexOf(source)

                val index = when (selectedOrderOption) {
                    R.string.selected_first -> if (!source.isSelected) 0 else indexOfSource
                    R.string.unselected_first -> if (!source.isSelected) indexOfSource else 0
                    else -> indexOfSource
                }

                remove(source)
                val source = source.copy(isSelected = source.isSelected.not())

                when (selectedVisibleOption) {
                    R.string.selected -> if (source.isSelected)
                        add(index, source)

                    R.string.unselected -> if (!source.isSelected)
                        add(index, source)

                    else -> add(index, source)

                }
            }
        else
            it.value
    }

}