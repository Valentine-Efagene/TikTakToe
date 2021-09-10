package com.valentyne.tiktaktoe.gameFragment

import androidx.navigation.fragment.findNavController
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.valentyne.tiktaktoe.R
import com.valentyne.tiktaktoe.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private val gameViewModel: GameViewModel by activityViewModels()
    private var icons: Map<Player, Int> =
        mapOf(Player.PLAYER_1 to R.drawable.ic_o, Player.PLAYER_2 to R.drawable.ic_x)

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.i(TAG, gameViewModel.test)

        gameViewModel.currentPlayerLiveData.observe(viewLifecycleOwner,
            { newPlayer ->
                binding.player.text = if (newPlayer == Player.PLAYER_1) "Player 1" else "Player 2"
            })

        gameViewModel.winnerLiveData.observe(viewLifecycleOwner,
            { winner ->
                binding.player.text = if (winner == Player.PLAYER_1) "Player 1 has won" else "Player 2 has won"

                if (winner != null){
                    findNavController().navigate(R.id.action_GameFragment_to_ScoreFragment)
                }
            })

        val gameTiles = mapOf(
            binding.gameTile1 to 1,
            binding.gameTile2 to 2,
            binding.gameTile3 to 3,
            binding.gameTile4 to 4,
            binding.gameTile5 to 5,
            binding.gameTile6 to 6,
            binding.gameTile7 to 7,
            binding.gameTile8 to 8,
            binding.gameTile9 to 9
        )

        for ((gameTile, tileNumber) in gameTiles) {
            gameTile.setOnClickListener {
                if (!gameViewModel.strategy[gameViewModel.currentPlayerLiveData.value]!!.contains(tileNumber)) {
                    gameTile.setImageResource(icons[gameViewModel.currentPlayerLiveData.value]!!)
                    gameViewModel.makeMove(tileNumber)
                    Log.i(TAG, gameViewModel.strategy.toString())
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "GameFragment"
    }
}