package gromcode.main.lesson19.homework;

import java.util.Arrays;

public class Demo_L19_H {

    public static void main(String[] args) {
        Controller controller = new Controller();
        try {
            File file1 = new File(1234567, "TestName1", "png", 454);
            File file2 = new File(2345671, "TestName2", "mp3", 6664);
            File file3 = new File(3456712, "TestName3", "mp4", 4545);
            File file111 = new File(1777767, "TestName1", "png", 454);

            File file4 = new File(4567123, "TestName4", "mp4", 4545);
            File file5 = new File(4534663, "TestName5", "Test", 4545);
            File file6 = new File(459652323, "TestName6", "mp4", 55554545);


            File file11 = new File(1777767, "TestName1", "png", 454);
            File file12 = new File(2345671, "TestName2", "mp3", 6664);
            File file13 = new File(3456712, "TestName3", "mp4", 4545);

            File file99 = new File(3456712, "TestName3", "mp4", 99999);

            File[] files1 = {file1, file2, file3, file111, null, null, null};
            File[] files2 = {file11, file12, file13, null, null, null};
            File[] files99 = {file99, null, null, null};
            File[] files4 = {file11, file12, file13, file1, null, null};

            String[] str1 = {"png", "mp3", "mp4"};
            String[] str2 = {"png", "mp3",};

            Storage storage1 = new Storage(3456712, files1, str1, "Ukraine", 100000);
            Storage storage2 = new Storage(6433712, files2, str1, "USA", 100000);
            Storage storage3 = new Storage(3466666, files2, str2, "Iran", 100000);
            Storage storage4 = new Storage(3488866, files99, str1, "Iran", 100000);
            Storage storage5 = new Storage(3444666, files4, str1, "Iran", 100000);

            //test put

            try {
                controller.put(storage1, file4);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println(Arrays.toString(files1));

            try {
                controller.put(storage1, file5);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            try {
                controller.put(storage1, file6);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            try {
                controller.put(storage1, file1);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            //test delete

            try {
                controller.delete(storage1, file1);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println(Arrays.toString(files1));

            try {
                controller.delete(storage1, file6);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println();

            //test transferAll

            try {
                controller.transferAll(storage1, storage2);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println(Arrays.toString(files1));
            System.out.println(Arrays.toString(files2));

            try {
                controller.transferAll(storage1, storage3);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            try {
                controller.transferAll(storage1, storage4);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            try {
                controller.transferAll(storage1, storage5);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println();

            //test transferFile

            try {
                controller.transferFile(storage1, storage5, 2345671);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            try {
                controller.transferFile(storage1, storage2, 4567123);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            System.out.println(Arrays.toString(files1));
            System.out.println(Arrays.toString(files2));

            try {
                controller.transferFile(storage1, storage3, 3456712);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            try {
                controller.transferFile(storage4, storage1, 3456712);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

            try {
                controller.transferFile(storage1, storage2, 1777767);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
