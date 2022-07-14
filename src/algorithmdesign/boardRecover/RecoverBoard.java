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
        System.out.println("������N��ֵ");
        Scanner in = new Scanner(System.in);
        int N = in.nextInt();
        int N2 = (int) Math.pow(2,N);
        JButton[][] jButton = new JButton[N2][N2];//����JButton����
        //��ʼ�����Ϊ��񲼾�
        setLayout(new GridLayout(N2,N2,0,0));
        //��ʼ����壬��JButton���������
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
 * tr��ʾ���ϽǷ�����к�
 * tc��ʾ���ϽǷ�����к�
 * dr��ʾ���ⷽ����к�
 * dc��ʾ���ⷽ����к�
 * size��ʾ���̵Ĺ�ģ
 */
//���η�
    private void chessBoard(int tr,int tc,int dr,int dc,int size,JButton[][] jButton) {
        if(size==1)
            return;
        int s=size/2;//�����̻���Ϊ�ĸ�С����
        //int t = tile++;//L�͹��ƺ�
        int t=(tile++)%colors.length;

//�������Ͻ�������
        if(dr<tr+s&&dc<tc+s)
            //�����ⷽ���򻮷�
            chessBoard(tr,tc,dr,dc,s,jButton);
        else
        //������ת��Ϊ���������ڻ���
        {
            jButton[tr+s-1][tc+s-1].setBackground(colors[t]);//��t�Ź��Ƹ������½�
            chessBoard(tr,tc,tr+s-1,tc+s-1,s,jButton);
        }

//�������Ͻ�������
        if(dr<tr+s && dc>=tc+s)
            //�����ⷽ���򻮷�
            chessBoard(tr,tc+s,dr,dc,s,jButton);
        else
        //������ת��Ϊ���������ڻ���
        {
            jButton[tr+s-1][tc+s].setBackground(colors[t]);//��t�Ź��Ƹ������½�
            chessBoard(tr,tc+s,tr+s-1,tc+s,s,jButton);
        }

//�������½�������
        if(dr>=tr+s && dc<tc+s)
            //�����ⷽ���򻮷�
            chessBoard(tr+s,tc,dr,dc,s,jButton);
        else
        //������ת��Ϊ���������ڻ���
        {
            jButton[tr+s][tc+s-1].setBackground(colors[t]);//��t�Ź��Ƹ������Ͻ�
            chessBoard(tr+s,tc,tr+s,tc+s-1,s,jButton);
        }


//�������½�������
        if(dr>=tr+s && dc>=tc+s)
            //�����ⷽ���򻮷�
            chessBoard(tr+s,tc+s,dr,dc,s,jButton);
        else
        //������ת��Ϊ���������ڻ���
        {
            jButton[tr+s][tc+s].setBackground(colors[t]);//��t�Ź��Ƹ������Ͻ�
            chessBoard(tr+s,tc+s,tr+s,tc+s,s,jButton);
        }
    }



    public static void init(){
        RecoverBoard board = new RecoverBoard();

        board.setTitle("���̸���");
        board.setSize(800,800);
        board.setLocationRelativeTo(null);//����������Ļ����
        board.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        board.setVisible(true);
    }


    public static void main(String[] args) {
        init();

    }
}
