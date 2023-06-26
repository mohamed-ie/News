package com.example.news.domain.use_case.sources

import com.example.news.R
import com.example.news.ui.common.Source
import javax.inject.Inject

class FilterSourcesUseCase @Inject constructor() {

    operator fun invoke(
        sources: Map<String, List<Source>>,
        selectedCategory: Int = R.string.language,
        selectedOrderOption: Int = R.string.unordered,
        selectedVisibleOption: Int = R.string.all,
        nameFilterQuery: String = ""
    ) = sources.values
        .flatten()
        //visible
        .filter {
            when (selectedVisibleOption) {
                R.string.selected -> it.isSelected
                R.string.unselected -> it.isSelected.not()
                else -> true
            }
        }
        //search
        .filter { it.name.contains(nameFilterQuery, true) }
        //group
        .groupBy {
            when (selectedCategory) {
                R.string.category -> it.category
                R.string.country -> it.country
                else -> it.language
            }
        }
        //order
        .mapValues {
            val value = it.value
                .toMutableList()

            when (selectedOrderOption) {
                R.string.selected_first -> value.sortedByDescending(Source::isSelected)
                R.string.unselected_first -> value.sortedBy(Source::isSelected)
                else -> it.value
            }
        }
}