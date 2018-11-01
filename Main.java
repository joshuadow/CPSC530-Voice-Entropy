import com.musicg.fingerprint.FingerprintSimilarity;
import com.musicg.wave.Wave;
import com.musicg.wave.WaveFileManager;

import javax.sound.midi.SysexMessage;
import java.io.File;
import java.io.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        Path currentRelativePath = Paths.get("");
        String s = currentRelativePath.toAbsolutePath().toString();
        File file1 = new File(args[0]);
        File file2 = new File(args[1]);
        file1.setReadable(true);
        file2.setReadable(true);
        byte[] file1Content = new byte[0];
        byte[] file2Content = new byte[0];
        try{
            file1Content = Files.readAllBytes(file1.toPath());
            file2Content = Files.readAllBytes(file2.toPath());
        }
        catch (IOException e){
            System.out.println("IO Error on Entropy Calculation");
        }
        System.out.println("Entropy of first file: " + Entropy.getEntropy(file1Content));
        System.out.println("Entropy of second file: " + Entropy.getEntropy(file2Content));

        Wave sound1 = new Wave(s+"/"+file1);
        Wave sound2 = new Wave(s+"/"+file2);
        FingerprintSimilarity sim = sound1.getFingerprintSimilarity(sound2);
        System.out.println("The files have a similarity score of: " + sim.getScore() + " matches per frame." +
                " 1 is average, anything >= 1.5 is most likely the same");

    }
}
