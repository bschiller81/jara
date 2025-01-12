/*
 * The MIT License
 *
 * Copyright 2020 Studio 42 GmbH (https://www.s42m.de).
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package de.s42.jara;

import de.s42.jara.assets.AssetManager;
import de.s42.jara.enitites.Scene;
import de.s42.jara.scenes.CarStill;
import de.s42.jara.scenes.Pearls;
import de.s42.jara.scenes.RoughnessMetalness;
import de.s42.jara.scenes.Transparent;
import java.awt.event.KeyEvent;

/**
 *
 * @author Benjamin Schiller
 */
public final class Configuration
{

	public enum ImageSaveFormat
	{
		JPG, PNG, BOTH
	}

	// Environment
	private static final String BASE_PATH = "./src/main/resources/"; // relative to working directory

	// UI
	// Highest tested resolution was 8K  (1920 * 4 x 1200 * 4)
	private final static double RENDER_SCALE = 4.0 / 1.0;
	private final static int DEFAULT_WIDTH = (int) (1920.0 * RENDER_SCALE);
	private final static int DEFAULT_HEIGHT = (int) (1200.0 * RENDER_SCALE);
	private final static int PREFERRED_TILESIZE = 15;
	private final static double WINDOW_SCALE = 1.0 / RENDER_SCALE;

	// Save to file
	private final static ImageSaveFormat SAVE_FORMAT = ImageSaveFormat.BOTH;
	private final static float JPG_QUALITY = 1.0f;
	private final static boolean SHOW_FOOTER_IN_FILES = true;

	// Raytracer
	private final static int MAX_PASSES = Integer.MAX_VALUE;
	private final static int THREADS = 24;
	private final static int RAY_DEPTH = 5;
	private final static int[] DIFFUSE_SUBSAMPLES = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	private final static int[] SPECULAR_SUBSAMPLES = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	private final static int[] REFRACTION_SUBSAMPLES = new int[]{0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
	private final static double CAMERA_DOF_SIZE = 0.05;
	private final static boolean CAMERA_AUTO_FOCUS = true;

	// Optimizations
	private final static int SPATIAL_TREE_MAX_DEPTH = 15;
	private final static int SPATIAL_TREE_SPLIT_NODE_SIZE = 10;

	// Scene
	public final static SceneLoader SCENE_LOADER = new Pearls();

	// Keys
	public final static int KEY_EXIT = KeyEvent.VK_ESCAPE;
	public final static int KEY_EXPOSURE_PLUS = KeyEvent.VK_PLUS;
	public final static int KEY_EXPOSURE_MINUS = KeyEvent.VK_MINUS;
	public final static int KEY_SAVE_IMAGE = KeyEvent.VK_S;

	public final static Scene createScene(AssetManager assets)
	{
		return SCENE_LOADER.loadScene(assets);
	}

	public final static int getTilesize()
	{
		// Calculcate next bigger tilesize than preferred which is a divisor of width and height
		int width = getWidth();
		int height = getHeight();
		int maxDivisor = (int) Math.max(width, height);
		for (int i = PREFERRED_TILESIZE; i <= maxDivisor; i++) {
			if ((width % i) == 0 && (height % i) == 0) {
				return i;
			}
		}

		// Worst case -> fall back to preferred tilesize
		return PREFERRED_TILESIZE;
	}

	public final static int getMaxPasses()
	{
		return MAX_PASSES;
	}

	public final static int getSpatialTreeSplitNodeSize()
	{
		return SPATIAL_TREE_SPLIT_NODE_SIZE;
	}

	public final static int getSpatialTreeMaxDepth()
	{
		return SPATIAL_TREE_MAX_DEPTH;
	}

	public final static double getCameraDofSize()
	{
		return CAMERA_DOF_SIZE;
	}

	public final static int getThreads()
	{
		return THREADS;
	}

	public final static int[] getDiffuseSubsamples()
	{
		return DIFFUSE_SUBSAMPLES;
	}

	public final static int[] getSpecularSubsamples()
	{
		return SPECULAR_SUBSAMPLES;
	}

	public final static int[] getRefractionSubsamples()
	{
		return REFRACTION_SUBSAMPLES;
	}

	public final static int getRayDepth()
	{
		return RAY_DEPTH;
	}

	public final static int getDefaultWidth()
	{
		return DEFAULT_WIDTH;
	}

	public final static int getDefaultHeight()
	{
		return DEFAULT_HEIGHT;
	}

	public final static SceneLoader getSceneLoader()
	{
		return SCENE_LOADER;
	}

	public final static int getWidth()
	{
		return Math.max(1, getSceneLoader().getPreferredWidth());
	}

	public final static int getHeight()
	{
		return Math.max(1, getSceneLoader().getPreferredHeight());
	}

	public final static ImageSaveFormat getSaveFormat()
	{
		return SAVE_FORMAT;
	}

	public final static float getJpgQuality()
	{
		return JPG_QUALITY;
	}

	public final static boolean getShowFooterInFiles()
	{
		return SHOW_FOOTER_IN_FILES;
	}

	public final static boolean getCameraAutoFocus()
	{
		return CAMERA_AUTO_FOCUS;
	}

	public final static double getWindowScale()
	{
		return WINDOW_SCALE;
	}

	public static String getBasePath()
	{
		return BASE_PATH;
	}

	private Configuration()
	{
		// Never instantiated
	}
}
