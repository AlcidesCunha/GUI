import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Platform;

public class Main extends Application
{
    TextArea textArea = new TextArea();

    // Create the Label
    Label statusLabel = new Label("Not Started...");

    // Create the Buttons
    Button startButton = new Button("Start");
    Button exitButton = new Button("Exit");

    @Override
    public void start(final Stage stage)
    {
    // Create the ButtonBox
        HBox buttonBox = new HBox(5, startButton, exitButton);

    // Create the VBox
        VBox root = new VBox(10, statusLabel, buttonBox,textArea);

    // Create the Scene
        Scene scene = new Scene(root,400,300);
    // Add the scene to the Stage
        stage.setScene(scene);
    // Set the title of the Stage
        stage.setTitle("Exemplo Threads GUI");
    // Display the Stage

        stage.show();
        startButton.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                startTask();
                System.out.println("Botão Start");
            }
        });

        exitButton.setOnAction(new EventHandler <ActionEvent>()
        {
            public void handle(ActionEvent event)
            {
                System.out.println("botao exit");
                stage.close();
            }
        });

    }

    void exit()
    {
        String status = "Saindo.";
        statusLabel.setText("Saindo");
        textArea.appendText("Saindo" + "\n");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void startTask() {
// Create a Runnable
        Runnable task = new Runnable() {
            public void run() {
                runTask2();
            }
        };
        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
// Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
// Start the thread
        backgroundThread.start();
}

    public void runTask2() {
        for(int i = 1; i <= 10; i++) {  try {
// Get the Status
            final String status = "Processing " + i + " of " + 10;
// javafx.application.Platform
            Platform.runLater(new Runnable() {
                @Override  public void run()
                {
                    //statusLabel.setText(status);
                }
            });
            textArea.appendText(status+"\n");  Thread.sleep(1000);
        } catch (InterruptedException e) {  e.printStackTrace();
        }
        }
    }




    public void runTask()
    {
        for(int i = 1; i <= 10; i++)
        {
            try
            {
                String status = "Processing " + i + " of " + 10;  statusLabel.setText(status);  textArea.appendText(status+"\n");

                Thread.sleep(1000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }


    public static void main(String args[])
    {
        Application.launch(args);
    }

}
