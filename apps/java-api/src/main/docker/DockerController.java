
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.InspectContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.core.DockerClientBuilder;

import java.io.IOException;
import java.util.List;

public class DockerManager {

    private DockerClient dockerClient;

    public DockerManager() {
        dockerClient = DockerClientBuilder.getInstance().build();
    }

    public boolean isContainerRunning(String containerName) {
        List<Container> containers = dockerClient.listContainersCmd().exec();
        for (Container container : containers) {
            for (String name : container.getNames()) {
                if (name.equals("/" + containerName)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void startContainer() throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("docker-compose", "up", "-d");
        builder.directory(new java.io.File(".")); // Make sure the docker-compose.yml is in the correct directory
        Process process = builder.start();
        process.waitFor();
    }

    public void initialize() {
        String containerName = "oracle23c";
        if (!isContainerRunning(containerName)) {
            try {
                startContainer();
                System.out.println("Docker container started successfully.");
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Docker container is already running.");
        }

        // Proceed with connecting to the Oracle DB
        connectToDatabase();
    }

    public void connectToDatabase() {
        // Implement your database connection logic here
    }

    public static void main(String[] args) {
        DockerManager dockerManager = new DockerManager();
        dockerManager.initialize();
    }
}
