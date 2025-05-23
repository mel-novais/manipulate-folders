import java.io.File;

public class count {
    public static void main(String[] args) {
        File mainFolder = new File("4-cx-1");
        if (!mainFolder.exists() || !mainFolder.isDirectory()) {
            System.out.println("Pasta não encontrada ou não é um diretório.");
            return;
        }

        File[] subfolders = mainFolder.listFiles(File::isDirectory);
        int count = 0;

        if (subfolders != null){
            for (File subfolder : subfolders){
                File[] files = subfolder.listFiles(File::isFile);
                int amount = files != null ? files.length : 0;
                System.out.println(subfolder.getName() + ": " + amount + " arquivos");
                count += amount;
            }
        }
        System.out.println("Total de arquivos: " + count);
    }
}