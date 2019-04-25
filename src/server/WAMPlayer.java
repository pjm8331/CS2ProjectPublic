package server;

import client.gui.WAMException;
import common.WAMProtocol;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class WAMPlayer implements WAMProtocol, Closeable {

    private Socket socket;

    private Scanner scanner;

    private PrintStream printStream;

    public WAMPlayer(Socket socket) throws WAMException{
        this.socket = socket;
        try {
            this.scanner = new Scanner(socket.getInputStream());
            this.printStream = new PrintStream(socket.getOutputStream());
        }
        catch (IOException e){
            throw new WAMException(e);
        }
    }

    public void connect(){
        printStream.println(WELCOME);
    }

    public void moleUp(){
        printStream.println(MOLE_UP);
    }

    public void moleDown(){
        printStream.println(MOLE_DOWN);
    }

    public void whack(){
        printStream.println(WHACK);
    }

    public void gameLost(){
        printStream.println(GAME_LOST);
    }

    public void gameWon(){
        printStream.println(GAME_WON);
    }

    public void gameTied(){
        printStream.println(GAME_TIED);
    }

    public void error(String message){
        printStream.println(ERROR + " " + message);
    }

    public int makeWhack(){
        return 0;
    }

    public void close(){
        try{
            socket.close();
        }
        catch (IOException e){}
    }
}