package algorithmdesign.boardRecover;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class RecoverBoard extends JFrame {

    int m=0,n=0;
    int tile = 0;
    Color[] colors = {Color.RED,Color.CYAN,Color.YELLOW,Color.GREEN};



    public RecoverBoard(){
        System.out.println("请输入N的值");
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int N2 = (int) Math.pow(2,N);
        JButton[][] jButton = new JButton[N2][N2];//设置JButton个数
        //初始化面板为表格布局
        setLayout(new GridLayout(N2,N2,0,0));
        //初始化面板，将JButton加入面板中
        for (int i = 0; i < N2; i++) {
            for (int j = 0; j < N2; j++) {
                jButton[i][j] = new JButton();
                jButton[i][j].setBackground(Color.BLACK);
                add(jButton[i][j]);
            }
        }

        for (int i = 0; i < N2; i++) {
            for (int j = 0; j < N2; j++) {
                jButton[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int k = 0; k < N2; k++) {
                            for (int l = 0; l < N2; l++) {
                                if (e.getSource()==jButton[k][l]){
                                    jButton[k][l].setBackground(Color.WHITE);
                                    m=k;n=l;
                                    chessBoard(0,0,m,n,N2,jButton);
                                }
                            }
                        }
                    }
                });
            }
        }

    }
/**
 * tr表示左上角方格的行号
 * tc表示左上角方格的列号
 * dr表示特殊方格的行号
 * dc表示特殊方格的列号
 * size表示棋盘的规模
 */
//分治法
    private void chessBoard(int tr,int tc,int dr,int dc,int size,JButton[][] jButton) {
        if(size==1)
            return;
        int s=size/2;//将棋盘划分为四个小棋盘
        //int t = tile++;//L型骨牌号
        int t=(tile++)%colors.length;

//覆盖左上角子棋盘
        if(dr<tr+s&&dc<tc+s)
            //有特殊方格则划分
            chessBoard(tr,tc,dr,dc,s,jButton);
        else
        //否则先转换为特殊棋盘在划分
        {
            jButton[tr+s-1][tc+s-1].setBackground(colors[t]);//用t号骨牌覆盖右下角
            chessBoard(tr,tc,tr+s-1,tc+s-1,s,jButton);
        }

//覆盖右上角子棋盘
        if(dr<tr+s && dc>=tc+s)
            //有特殊方格则划分
            chessBoard(tr,tc+s,dr,dc,s,jButton);
        else
        //否则先转换为特殊棋盘在划分
        {
            jButton[tr+s-1][tc+s].setBackground(colors[t]);//用t号骨牌覆盖左下角
            chessBoard(tr,tc+s,tr+s-1,tc+s,s,jButton);
        }

//覆盖左下角子棋盘
        if(dr>=tr+s && dc<tc+s)
            //有特殊方格则划分
            chessBoard(tr+s,tc,dr,dc,s,jButton);
        else
        //否则先转换为特殊棋盘在划分
        {
            jButton[tr+s][tc+s-1].setBackground(colors[t]);//用t号骨牌覆盖右上角
            chessBoard(tr+s,tc,tr+s,tc+s-1,s,jButton);
        }


//覆盖右下角子棋盘
        if(dr>=tr+s && dc>=tc+s)
            //有特殊方格则划分
            chessBoard(tr+s,tc+s,dr,dc,s,jButton);
        else
        //否则先转换为特殊棋盘在划分
        {
            jButton[tr+s][tc+s].setBackground(colors[t]);//用t号骨牌覆盖左上角
            chessBoard(tr+s,tc+s,tr+s,tc+s,s,jButton);
        }
    }



    public static void init(){
        RecoverBoard board = new RecoverBoard();

        board.setTitle("棋盘覆盖");
        board.setSize(800,800);
        board.setLocationRelativeTo(null);//面板出现在屏幕中央
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setVisible(true);
    }


    public static void main(String[] args) {
        init();

    }
}
