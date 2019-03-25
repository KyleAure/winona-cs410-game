package edu.winona.cs.image;

import edu.winona.cs.log.Log;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.imgscalr.Scalr;

/**
 * Image Processing Class
 *
 * This class divides the images into multiple smaller images and processes
 * movement
 *
 * How to use: Step 1) create image processor Step 2) call assignImage() - pass
 * image URL (file-chooser?), returns true if valid Step 3) if true, call
 * divideImage() - pass divisions, returns list of images Step 4) call
 * getCoordinateList() - returns coordinates where images are placed
 *
 * Copy the dividedImageList, shuffle carefully, then compare lists after user
 * interaction
 *
 * @author Tristin Harvell
 */
public class ImageProcessor {

    private static final Log LOG = new Log("ImageProcessor");

    private BufferedImage image;
    private Boolean isValidURL;
    private File file;
    private List<BufferedImage> dividedImageList = new ArrayList<BufferedImage>();
    private List<Point> imageCoordinateList = new ArrayList<Point>();
    private Point point = new Point();

    /**
     * Assign image
     *
     * @param imageURL This is the URL that directs to the image to be used.
     * @throws java.io.IOException
     * @require A valid URL that links directs to an image.
     * @ensure The associated image is selected.
     * @return Returns if the image was selected successfully.
     */
    @SuppressWarnings("empty-statement")
    public void assignImage(File imageURL) throws IOException {

        //assign the image to be divided
        image = ImageIO.read(imageURL);

        //resize the image to suitable dimensions (and a square)
        image = Scalr.resize(image, 300);
    }

    /**
     * Divide image
     *
     * @param divisions This is the number of times the images is to be divided.
     * @require A valid image is preassigned and the division number is valid.
     * @ensure The associated image is divided according to the chosen
     * divisions.
     * @return Returns an array of images that are in correct (unscrambled)
     * order.
     */
    public List<BufferedImage> divideImage(int divisions) {

        //gets image dimensions inorder to divide the image into equal parts
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        //used to get the subimage
        BufferedImage croppedImage;

        //reset
        dividedImageList.clear();
        int sectionWidth = 0;
        int sectionHeight = 0;
        imageCoordinateList.clear();

        switch (divisions) {
            case 4: //not meant to be implemented, but left for testing purposes
                sectionWidth = imageWidth / 2;
                sectionHeight = imageHeight / 2;

                //add matching coordinates
                point.setLocation(0, 0);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, 0);
                imageCoordinateList.add(point);
                point.setLocation(0, sectionHeight);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, sectionHeight);
                imageCoordinateList.add(point);

                //top left 1
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 0, sectionWidth * 1, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //top right 2
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 0, sectionWidth * 1, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //bottom left 3
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 1, sectionWidth * 1, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //bottom right 4
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 1, sectionWidth * 1, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                break;
            case 9: //when the user selects "easy" the image is divided 9 times
                sectionWidth = imageWidth / 3;
                sectionHeight = imageHeight / 3;

                //add matching coordinates
                //row 1
                point.setLocation(0, 0);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, 0);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, 0);
                imageCoordinateList.add(point);
                //row 2
                point.setLocation(0, sectionHeight);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, sectionHeight);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, sectionHeight);
                imageCoordinateList.add(point);
                //row 3
                point.setLocation(0, sectionHeight * 2);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, sectionHeight * 2);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, sectionHeight * 2);
                imageCoordinateList.add(point);

                //top left 1
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 0, sectionWidth * 1, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //top middle 2
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 0, sectionWidth * 2, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //top right 3
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 0, sectionWidth * 3, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //middle left 4
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 1, sectionWidth * 1, sectionHeight * 2);
                dividedImageList.add(croppedImage);
                //middle middle 5
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 1, sectionWidth * 2, sectionHeight * 2);
                dividedImageList.add(croppedImage);
                //middle right 6
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 1, sectionWidth * 3, sectionHeight * 2);
                dividedImageList.add(croppedImage);
                //bottom left 7
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 2, sectionWidth * 1, sectionHeight * 3);
                dividedImageList.add(croppedImage);
                //bottom middle 8
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 2, sectionWidth * 2, sectionHeight * 3);
                dividedImageList.add(croppedImage);
                //bottom right 9
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 2, sectionWidth * 3, sectionHeight * 3);
                dividedImageList.add(croppedImage);
                break;
            case 16: //when the user selects "medium" the image is divided 16 times
                sectionWidth = imageWidth / 4;
                sectionHeight = imageHeight / 4;

                //add matching coordinates
                //row 1
                point.setLocation(0, 0);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, 0);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, 0);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 3, 0);
                imageCoordinateList.add(point);
                //row 2
                point.setLocation(0, sectionHeight);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, sectionHeight);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, sectionHeight);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 3, sectionHeight);
                imageCoordinateList.add(point);
                //row 3
                point.setLocation(0, sectionHeight * 2);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, sectionHeight * 2);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, sectionHeight * 2);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 3, sectionHeight * 2);
                imageCoordinateList.add(point);
                //row 4
                point.setLocation(0, sectionHeight * 3);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, sectionHeight * 3);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, sectionHeight * 3);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 3, sectionHeight * 4);
                imageCoordinateList.add(point);

                //top left 1
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 0, sectionWidth * 1, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //top middle-left 2
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 0, sectionWidth * 2, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //top middle-right 3
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 0, sectionWidth * 3, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //top right 4
                croppedImage = image.getSubimage(sectionWidth * 3, sectionHeight * 0, sectionWidth * 4, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //middle-top left 5
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 1, sectionWidth * 1, sectionHeight * 2);
                dividedImageList.add(croppedImage);
                //middle-top middle-left 6
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 1, sectionWidth * 2, sectionHeight * 2);
                dividedImageList.add(croppedImage);
                //middle-top middle-right 7 
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 1, sectionWidth * 3, sectionHeight * 2);
                dividedImageList.add(croppedImage);
                //middle-top right 8
                croppedImage = image.getSubimage(sectionWidth * 3, sectionHeight * 1, sectionWidth * 4, sectionHeight * 2);
                dividedImageList.add(croppedImage);
                //middle-bottom left 9
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 2, sectionWidth * 1, sectionHeight * 3);
                dividedImageList.add(croppedImage);
                //middle-bottom middle-left 10
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 2, sectionWidth * 2, sectionHeight * 3);
                dividedImageList.add(croppedImage);
                //middle-bottom middle-right 11
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 2, sectionWidth * 3, sectionHeight * 3);
                dividedImageList.add(croppedImage);
                //middle-bottom right 12
                croppedImage = image.getSubimage(sectionWidth * 3, sectionHeight * 2, sectionWidth * 4, sectionHeight * 3);
                dividedImageList.add(croppedImage);
                //bottom left 13
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 3, sectionWidth * 1, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                //bottom middle-left 14
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 3, sectionWidth * 2, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                //bottom middle-right 15
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 3, sectionWidth * 3, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                //bottom right 16
                croppedImage = image.getSubimage(sectionWidth * 3, sectionHeight * 3, sectionWidth * 4, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                break;
            case 25: //when the user selects "hard" the image is divided 25 times
                sectionWidth = imageWidth / 5;
                sectionHeight = imageHeight / 5;

                //add matching coordinates
                //row 1
                point.setLocation(0, 0);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, 0);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, 0);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 3, 0);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 4, 0);
                imageCoordinateList.add(point);
                //row 2
                point.setLocation(0, sectionHeight);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, sectionHeight);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, sectionHeight);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 3, sectionHeight);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 4, sectionHeight);
                imageCoordinateList.add(point);
                //row 3
                point.setLocation(0, sectionHeight * 2);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, sectionHeight * 2);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, sectionHeight * 2);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 3, sectionHeight * 2);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 4, sectionHeight * 2);
                imageCoordinateList.add(point);
                //row 4
                point.setLocation(0, sectionHeight * 3);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, sectionHeight * 3);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, sectionHeight * 3);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 3, sectionHeight * 4);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 4, sectionHeight * 5);
                imageCoordinateList.add(point);
                //row 4
                point.setLocation(0, sectionHeight * 3);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth, sectionHeight * 3);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 2, sectionHeight * 3);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 3, sectionHeight * 4);
                imageCoordinateList.add(point);
                point.setLocation(sectionWidth * 4, sectionHeight * 5);
                imageCoordinateList.add(point);

                //top left 1
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 0, sectionWidth * 1, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //top middle-left 2
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 0, sectionWidth * 2, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //top middle-middle 3
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 0, sectionWidth * 3, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //top middle-right 4
                croppedImage = image.getSubimage(sectionWidth * 3, sectionHeight * 0, sectionWidth * 4, sectionHeight * 1);
                dividedImageList.add(croppedImage);
                //top right 5
                croppedImage = image.getSubimage(sectionWidth * 4, sectionHeight * 0, sectionWidth * 5, sectionHeight * 1);
                dividedImageList.add(croppedImage);

                //middle-top left 6
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 1, sectionWidth * 1, sectionHeight * 2);
                dividedImageList.add(croppedImage);
                //middle-top middle-left 7
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 1, sectionWidth * 2, sectionHeight * 2);
                dividedImageList.add(croppedImage);
                //middle-top middle-middle 8 
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 1, sectionWidth * 3, sectionHeight * 2);
                dividedImageList.add(croppedImage);
                //middle-top middle-right 9
                croppedImage = image.getSubimage(sectionWidth * 3, sectionHeight * 1, sectionWidth * 4, sectionHeight * 2);
                dividedImageList.add(croppedImage);
                //middle-top right 10
                croppedImage = image.getSubimage(sectionWidth * 4, sectionHeight * 1, sectionWidth * 5, sectionHeight * 2);
                dividedImageList.add(croppedImage);

                //middle-midle left 11
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 2, sectionWidth * 1, sectionHeight * 3);
                dividedImageList.add(croppedImage);
                //middle-middle middle-left 12
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 2, sectionWidth * 2, sectionHeight * 3);
                dividedImageList.add(croppedImage);
                //middle-middle middle-middle 13
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 2, sectionWidth * 3, sectionHeight * 3);
                dividedImageList.add(croppedImage);
                //middle-middle middle-right 14
                croppedImage = image.getSubimage(sectionWidth * 3, sectionHeight * 2, sectionWidth * 4, sectionHeight * 3);
                dividedImageList.add(croppedImage);
                //middle-middle right 15
                croppedImage = image.getSubimage(sectionWidth * 4, sectionHeight * 2, sectionWidth * 5, sectionHeight * 3);
                dividedImageList.add(croppedImage);

                //middle-bottom left 16
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 3, sectionWidth * 1, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                //middle-bottom middle-left 17
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 3, sectionWidth * 2, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                //middle-bottom middle-middle 18
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 3, sectionWidth * 3, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                //middle-bottom middle-right 19
                croppedImage = image.getSubimage(sectionWidth * 3, sectionHeight * 3, sectionWidth * 4, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                //middle-bottom right 20
                croppedImage = image.getSubimage(sectionWidth * 4, sectionHeight * 3, sectionWidth * 5, sectionHeight * 4);
                dividedImageList.add(croppedImage);

                //bottom left 21
                croppedImage = image.getSubimage(sectionWidth * 0, sectionHeight * 3, sectionWidth * 1, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                //bottom middle-left 22
                croppedImage = image.getSubimage(sectionWidth * 1, sectionHeight * 3, sectionWidth * 2, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                //bottom middle-middle 23
                croppedImage = image.getSubimage(sectionWidth * 2, sectionHeight * 3, sectionWidth * 3, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                //bottom middle-right 24
                croppedImage = image.getSubimage(sectionWidth * 3, sectionHeight * 3, sectionWidth * 4, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                //bottom right 25
                croppedImage = image.getSubimage(sectionWidth * 4, sectionHeight * 3, sectionWidth * 5, sectionHeight * 4);
                dividedImageList.add(croppedImage);
                break;
            default:
                break;
        }
        return dividedImageList;
    }

    /**
     * Get image coordinates list
     *
     * @require The associated image has already been divided.
     * @ensure All the coordinates are listed according to the number of
     * divisions.
     * @return Returns an array of image coordinates for the layout
     */
    public List<Point> getCoordinateList() {
        if (imageCoordinateList != null) {
            return imageCoordinateList;
        } else {
            System.out.print("Error: imageCoordinateList is null");
            return null;
        }
    }

    /**
     * Get divided image list
     *
     * @require The associated image has already been divided.
     * @ensure All the images are listed according to number of divisions.
     * @return Returns an array of the divided images
     */
    public List<BufferedImage> getDividedImageList() {
        if (dividedImageList != null) {
            return dividedImageList;
        } else {
            System.out.print("Error: dividedImageList is null");
            return null;
        }
    }
}
