package ru.mobile.lukslol.view.start.regionselector

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.dialog_region_selector.*
import ru.mobile.lukslol.R
import ru.mobile.lukslol.di.Components
import ru.mobile.lukslol.util.view.addOneShotLayoutChangeListener
import ru.mobile.lukslol.util.view.decorations.ItemDividerDecoration
import ru.mobile.lukslol.view.start.EnterSummonerMutation.RegionChanged
import ru.mobile.lukslol.view.start.EnterSummonerViewModel
import javax.inject.Inject

class RegionSelectorDialog : BottomSheetDialogFragment() {

    @Inject
    lateinit var viewModel: EnterSummonerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Components.enterSummonerComponent.get()?.inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.dialog_region_selector, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        region_selector_list.apply {
            layoutManager = LinearLayoutManager(context)
            setController(RegionSelectorController { region ->
                viewModel.mutate(RegionChanged(region))
                dismiss()
            })
            addItemDecoration(ItemDividerDecoration(context, R.color.dividerColor, R.dimen.divider_size))
        }

        region_selector_list.addOneShotLayoutChangeListener {
            BottomSheetBehavior.from(view.parent as View).peekHeight = view.height
        }
    }
}