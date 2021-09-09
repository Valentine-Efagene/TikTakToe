package com.valentyne.tiktaktoe.gameFragment

//import androidx.navigation.fragment.findNavController
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.valentyne.tiktaktoe.R
import com.valentyne.tiktaktoe.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class GameFragment : Fragment() {
    private var _binding: FragmentGameBinding? = null
    private lateinit var gameViewModel: GameViewModel

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }*/

        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        Log.i(TAG, gameViewModel.test)

        this.binding.player.text =
            if (gameViewModel.player == Player.PLAYER_1) "Player 1" else "Player 2"

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
                if (!gameViewModel.strategy.contains(tileNumber)) {
                    gameTile.setImageResource(R.drawable.ic_o)
                    gameViewModel.strategy.add(tileNumber)
                    Log.d(TAG, gameViewModel.strategy.toString())
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