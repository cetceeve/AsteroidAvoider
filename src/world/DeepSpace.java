package world;

import constants.Constants;
import de.ur.mi.graphics.Image;
import de.ur.mi.util.RandomGenerator;

/**
 * control background image and moving particles
 * note that particle speeds are matched to the obstacle speed
 */
public class DeepSpace {
    private static final String DEEP_SPACE_IMAGE = "data/assets/deepspace_blue.png";
    private static final int PARTICLE_NUM = 15;

    private RandomGenerator randomGenerator;
    private Image deepSpace;
    private Particle particles[];
    private int obstacleSpeed;

    public DeepSpace(int obstacleSpeed) {
        randomGenerator = RandomGenerator.getInstance();
        this.obstacleSpeed = obstacleSpeed;
        deepSpace = new Image(0, 0, DEEP_SPACE_IMAGE);
        particles = new Particle[PARTICLE_NUM];
        initParticles();
    }

    // particles run from the top of the screen down and get reused once they leave at the bottom
    public void update() {
        for (int i = 0; i < PARTICLE_NUM; i++) {
            particles[i].update();
            if (particles[i].hasLeftScreen()) {
                redrawParticle(particles[i]);
            }
        }
    }

    public void draw() {
        deepSpace.draw();
        for (int i = 0; i < PARTICLE_NUM; i++) {
            particles[i].draw();
        }
    }

    private void initParticles() {
        for (int i = 0; i < PARTICLE_NUM; i++) {
            int[] rPV = randomParticleValues();
            particles[i] = new Particle(rPV[0], 0 - rPV[1],0 - rPV[2], rPV[3]);
        }
    }

    private void redrawParticle(Particle particle) {
        int[] rPV = randomParticleValues();
        particle.setNewValues(rPV[0], 0 - rPV[1],0 - rPV[2], rPV[3]);
    }

    // particles are random with very specific constraints to make them feel right
    private int[] randomParticleValues() {
        int linePosX = randomGenerator.nextInt(0, Constants.CANVAS_WIDTH);
        int lineStart = randomGenerator.nextInt(0, 700);
        int lineLength = randomGenerator.nextInt(500, 700);
        int particleSpeed = (int)(obstacleSpeed * randomGenerator.nextDouble(4.0, 5.0));
        return new int[]{linePosX, lineStart, lineStart + lineLength, particleSpeed};
    }

    // particle speeds change with the obstacle speed for each level
    public void setObstacleSpeed(int obstacleSpeed) {
        this.obstacleSpeed = obstacleSpeed;
    }
}
