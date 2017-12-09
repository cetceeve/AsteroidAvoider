package world;

import constants.Constants;
import de.ur.mi.graphics.Image;
import de.ur.mi.util.RandomGenerator;

public class DeepSpace {
    //constants
    private static final String DEEP_SPACE_IMAGE = "data/assets/deepspace_blue.jpg";
    private static final float IMAGE_START_X = -800f;
    private static final float IMAGE_START_Y = -420f;
    private static final int PARTICLE_NUMBER = 15;
    // Instance variables
    private RandomGenerator randomGenerator;
    private Image deepSpace;
    private int obstacleSpeed;
    private Particle particles[];

    public DeepSpace(int obstacleSpeed) {
        randomGenerator = RandomGenerator.getInstance();
        this.obstacleSpeed = obstacleSpeed;
        deepSpace = new Image(IMAGE_START_X, IMAGE_START_Y, DEEP_SPACE_IMAGE);
        particles = new Particle[PARTICLE_NUMBER];
        initParticles();
    }

    public void update() {
        for (int i = 0; i < PARTICLE_NUMBER; i++) {
            particles[i].update();
        }
    }

    public void draw() {
        deepSpace.draw();
        for (int i = 0; i < PARTICLE_NUMBER; i++) {
            particles[i].draw();
            if (particles[i].hasLeftScreen()) {
                redrawParticles(particles[i]);
            }
        }
    }

    public void setObstacleSpeed(int obstacleSpeed) {
        this.obstacleSpeed = obstacleSpeed;
    }

    private void initParticles() {
        for (int i = 0; i < PARTICLE_NUMBER; i++) {
            int[] rPV = randomParticleValues();
            particles[i] = new Particle(rPV[0], 0 - rPV[1],0 - rPV[2], rPV[3]);
        }
    }

    private void redrawParticles(Particle particle) {
        int[] rPV = randomParticleValues();
        particle.setNewValues(rPV[0], 0 - rPV[1],0 - rPV[2], rPV[3]);
    }

    private int[] randomParticleValues() {
        int linePosX = randomGenerator.nextInt(0, Constants.CANVAS_WIDTH);
        int lineStart = randomGenerator.nextInt(0, 700);
        int lineLength = randomGenerator.nextInt(500, 700);
        int particleSpeed = (int)(obstacleSpeed * randomGenerator.nextDouble(4.0, 5.0));
        return new int[]{linePosX, lineStart, lineStart + lineLength, particleSpeed};
    }
}
