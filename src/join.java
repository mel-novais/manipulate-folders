import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class join {
    public static void main(String[] args) {
        String[] nameFolders = {"4-cx-1", "4-cx-2", "4-cx-3"};
        File outFolder = new File("outFolder");
        if (!outFolder.exists()) {
            boolean criada = outFolder.mkdirs();
            if (!criada) {
                System.out.println("Não foi possível criar a pasta 'saida'");
                return;
            }
        }

        for (String nameFolder : nameFolders) {
            File folder = new File(nameFolder);
            if (!folder.exists() || !folder.isDirectory()) {
                System.out.println("Pasta não encontrada ou não é um diretório:" + nameFolder);
                continue;
            }
            File[] subfolders = folder.listFiles(File::isDirectory);
            if (subfolders == null) continue;
            for (File subfolder : subfolders) {
                File[] files = subfolder.listFiles((dir, name) -> name.toLowerCase().endsWith(".xml"));
                if (files == null) continue;
                for (File file : files) {
                    Path origin = file.toPath();
                    Path destination = new File(outFolder, file.getName()).toPath();
                    try {
                        Files.copy(origin, destination, StandardCopyOption.REPLACE_EXISTING);
                        System.out.println("Copiado: " + file.getName() + " para saida/");
                    } catch (IOException e) {
                        System.out.println("Erro ao copiar: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Todos os arquivos XML foram copiados para a pasta 'saida'");
        }
    }
}
