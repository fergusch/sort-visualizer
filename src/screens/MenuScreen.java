package screens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import main.SortVisualizerGUI;

/**
 * Menu screen class built with Swing.
 */
public class MenuScreen extends JPanel {

	private static final long serialVersionUID = 1L;

	public MenuScreen(SortVisualizerGUI frame) throws IOException {
		
		// Set up the panel
		this.setPreferredSize(new Dimension(1280, 720));
		this.setBackground(new Color(230, 230, 230));
		this.setFocusable(true);
		setLayout(null);
		
		// Create labels, buttons, etc.
		// Each button gets an ActionListener added to it that 
		// loads the VisualizationScreen class with the sorting
		// algorithm represented by the button when clicked.
		
		JLabel lblSortVisualizer = new JLabel("Sort Visualizer");
		lblSortVisualizer.setHorizontalAlignment(SwingConstants.LEFT);
		lblSortVisualizer.setFont(new Font("Roboto", Font.PLAIN, 64));
		lblSortVisualizer.setForeground(new Color(20, 20, 20));
		lblSortVisualizer.setBounds(70, 49, 637, 94);
		add(lblSortVisualizer);
		
		JLabel lblChart = new JLabel(new ImageIcon(ImageIO.read( ClassLoader.getSystemResource("bigo_chart.jpg"))));
		lblChart.setBounds(715, 21, 513, 362);
		add(lblChart);
		
		JTextPane txtpnToSeeA = new JTextPane();
		txtpnToSeeA.setEditable(false);
		txtpnToSeeA.setFont(new Font("Roboto", Font.PLAIN, 20));
		txtpnToSeeA.setForeground(new Color(20, 20, 20));
		txtpnToSeeA.setBackground(new Color(230, 230, 230));
		txtpnToSeeA.setText("To see a sorting algorithm, click the respective button below." + 
						"\n\nSorting algorithms are ordered by time complexity. Time complexity" + 
						" is a measure of the amount of time an algorithm takes to complete its " + 
						"assigned task with respect to the size of the input array, n. A helpful" + 
						" chart is displayed on the right. Non-comparison sorts are close to O(n).");
		txtpnToSeeA.setBounds(70, 150, 603, 190);
		add(txtpnToSeeA);
		
		JLabel btnSource = new JLabel("Chart source: http://bigocheatsheet.com/");
		btnSource.setFont(new Font("Roboto", Font.BOLD, 14));
		btnSource.setForeground(new Color(20, 20, 20));
		btnSource.setBounds(73, 350, 300, 30);
		add(btnSource);
		
		JLabel lblOn2Algorithms = new JLabel("O(n^2) algorithms");
		lblOn2Algorithms.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblOn2Algorithms.setForeground(new Color(20, 20, 20));
		lblOn2Algorithms.setBounds(70, 411, 173, 37);
		add(lblOn2Algorithms);
		
		JButton btnBubble = new JButton("Bubble sort");
		btnBubble.setFont(new Font("Roboto", Font.BOLD, 18));
		btnBubble.setForeground(new Color(20, 20, 20));
		btnBubble.setBounds(70, 460, 183, 55);
		btnBubble.setBackground(new Color(210, 210, 210));
		btnBubble.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VisualizationScreen vs = new VisualizationScreen(Sort.BUBBLE, frame);
				frame.setContentPane(vs);
				frame.pack();
			}
			
		});
		add(btnBubble);
		
		JButton btnInsertionSort = new JButton("Insertion sort");
		btnInsertionSort.setFont(new Font("Roboto", Font.BOLD, 18));
		btnInsertionSort.setForeground(new Color(20, 20, 20));
		btnInsertionSort.setBounds(70, 527, 183, 55);
		btnInsertionSort.setBackground(new Color(210, 210, 210));
		btnInsertionSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VisualizationScreen vs = new VisualizationScreen(Sort.INSERTION, frame);
				frame.setContentPane(vs);
				frame.pack();
			}
			
		});
		add(btnInsertionSort);
		
		JButton btnSelectionSort = new JButton("Selection sort");
		btnSelectionSort.setFont(new Font("Roboto", Font.BOLD, 18));
		btnSelectionSort.setForeground(new Color(20, 20, 20));
		btnSelectionSort.setBounds(70, 594, 183, 55);
		btnSelectionSort.setBackground(new Color(210, 210, 210));
		btnSelectionSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VisualizationScreen vs = new VisualizationScreen(Sort.SELECTION, frame);
				frame.setContentPane(vs);
				frame.pack();
			}
			
		});
		add(btnSelectionSort);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(279, 402, 2, 236);
		add(separator);
		
		JLabel lblOnlognAlgorithms = new JLabel("O(n log n) algorithms");
		lblOnlognAlgorithms.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblOnlognAlgorithms.setForeground(new Color(20, 20, 20));
		lblOnlognAlgorithms.setBounds(310, 411, 259, 37);
		add(lblOnlognAlgorithms);
		
		JButton btnQuicksort = new JButton("Quicksort");
		btnQuicksort.setFont(new Font("Roboto", Font.BOLD, 18));
		btnQuicksort.setForeground(new Color(20, 20, 20));
		btnQuicksort.setBounds(310, 460, 183, 55);
		btnQuicksort.setBackground(new Color(210, 210, 210));
		btnQuicksort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VisualizationScreen vs = new VisualizationScreen(Sort.QUICK, frame);
				frame.setContentPane(vs);
				frame.pack();
			}
			
		});
		add(btnQuicksort);
		
		JButton btnMergeSort = new JButton("Merge sort");
		btnMergeSort.setFont(new Font("Roboto", Font.BOLD, 18));
		btnMergeSort.setForeground(new Color(20, 20, 20));
		btnMergeSort.setBounds(310, 527, 183, 55);
		btnMergeSort.setBackground(new Color(210, 210, 210));
		btnMergeSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VisualizationScreen vs = new VisualizationScreen(Sort.MERGE, frame);
				frame.setContentPane(vs);
				frame.pack();
			}
			
		});
		add(btnMergeSort);
		
		JButton btnShellSort = new JButton("Shell sort");
		btnShellSort.setFont(new Font("Roboto", Font.BOLD, 18));
		btnShellSort.setForeground(new Color(20, 20, 20));
		btnShellSort.setBounds(310, 594, 183, 55);
		btnShellSort.setBackground(new Color(210, 210, 210));
		btnShellSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VisualizationScreen vs = new VisualizationScreen(Sort.SHELL, frame);
				frame.setContentPane(vs);
				frame.pack();
			}
			
		});
		add(btnShellSort);
		
		JSeparator separator2 = new JSeparator();
		separator2.setOrientation(SwingConstants.VERTICAL);
		separator2.setBounds(521, 422, 2, 236);
		add(separator2);
		
		JLabel lblNonCompareAlgorithms = new JLabel("Non-comparison algorithms");
		lblNonCompareAlgorithms.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNonCompareAlgorithms.setForeground(new Color(20, 20, 20));
		lblNonCompareAlgorithms.setBounds(550, 411, 259, 37);
		add(lblNonCompareAlgorithms);
		
		JButton btnCountingSort = new JButton("Counting sort");
		btnCountingSort.setFont(new Font("Roboto", Font.BOLD, 18));
		btnCountingSort.setForeground(new Color(20, 20, 20));
		btnCountingSort.setBounds(550, 460, 183, 55);
		btnCountingSort.setBackground(new Color(210, 210, 210));
		btnCountingSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VisualizationScreen vs = new VisualizationScreen(Sort.COUNTING, frame);
				frame.setContentPane(vs);
				frame.pack();
			}
			
		});
		add(btnCountingSort);
		
		JButton btnRadixLSDSort = new JButton("Radix sort (LSD)");
		btnRadixLSDSort.setFont(new Font("Roboto", Font.BOLD, 18));
		btnRadixLSDSort.setForeground(new Color(20, 20, 20));
		btnRadixLSDSort.setBounds(550, 527, 183, 55);
		btnRadixLSDSort.setBackground(new Color(210, 210, 210));
		btnRadixLSDSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VisualizationScreen vs = new VisualizationScreen(Sort.RADIX, frame);
				frame.setContentPane(vs);
				frame.pack();
			}
			
		});
		add(btnRadixLSDSort);
		
		JLabel lblOtherAlgorithms = new JLabel("Other algorithms");
		lblOtherAlgorithms.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblOtherAlgorithms.setForeground(new Color(20, 20, 20));
		lblOtherAlgorithms.setBounds(1030, 411, 259, 37);
		add(lblOtherAlgorithms);
		
		JButton btnSlowSort = new JButton("Slow sort");
		btnSlowSort.setFont(new Font("Roboto", Font.BOLD, 18));
		btnSlowSort.setForeground(new Color(20, 20, 20));
		btnSlowSort.setBounds(1030, 460, 183, 55);
		btnSlowSort.setBackground(new Color(210, 210, 210));
		btnSlowSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				VisualizationScreen vs = new VisualizationScreen(Sort.SLOW, frame);
				frame.setContentPane(vs);
				frame.pack();
			}
			
		});
		add(btnSlowSort);
		
	}

}
