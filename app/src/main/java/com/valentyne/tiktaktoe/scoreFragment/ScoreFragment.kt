package com.valentyne.tiktaktoe.scoreFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.valentyne.tiktaktoe.R
import com.valentyne.tiktaktoe.databinding.FragmentScoreBinding
import com.valentyne.tiktaktoe.gameFragment.GameViewModel
import com.valentyne.tiktaktoe.gameFragment.Player

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ScoreFragment : Fragment() {

    private var _binding: FragmentScoreBinding? = null
    private val gameViewModel: GameViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnBackToGame.setOnClickListener {
            findNavController().navigate(R.id.action_ScoreFragment_to_GameFragment)
        }

        binding.textviewSecond.text = resources.getString(
            R.string.score_string,
            gameViewModel.score[Player.PLAYER_1],
            gameViewModel.score[Player.PLAYER_2],
            gameViewModel.drawCount
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}