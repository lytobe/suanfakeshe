package algorithmdesign.chinaMap;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


import java.io.*;

public class ChinaMap {
    private static final int NUMBER = 32;
    private String[] board = new String[NUMBER];
    private int[][] edge = new int[NUMBER][NUMBER];
    //��ʼ�����ж����Ϳɫ0
    private int[] colors = new int[NUMBER];

    public ChinaMap(String[] board, int[][] edge) {
        this.board = board;
        this.edge = edge;
    }

    public boolean color(int colorKind) {
        int cityNode = 0;    //��ͼ�е�һ��ʡ��ʼ
        //cityNode��0~BOARD_NUMBER-1��ʾ����ʡ
        while(cityNode <NUMBER) {
            int flag = 1;//����whileѭ���ı�־
            //�������colorKind����ɫ�������㣬����false
            if(cityNode == -1) {
                return false;
            }
            colors[cityNode]++;//color[cityNode]+1,�˴���Ϊͳ�ƼӺų��ֵĴ����������ж�������ɫ
            //��Ϳɫ����colorKind�֣����ݣ����ص���һ��������Ȼ��������нڵ���ɫ������Ϊ0����������
            if(colors[cityNode] > colorKind) {
                colors[cityNode] = 0;
                cityNode--;
                continue;
            }
            for(int i = 0; i < cityNode; ++i) {
                //�����ڽӵ�Ϳ��ͬ��ɫ
                if (edge[cityNode][i] == 1 && colors[cityNode] == colors[i]) {
                    flag = 0;
                    break;
                }
            }
            //�����ڽӵ�Ϳ��ͬ��ɫ,�ý����Ϳ
            if(flag == 0) {
                continue;
            }
            cityNode++;//�����Ҫ����Ϳ��һ�����нڵ�
        }
        for(int i = 0; i <NUMBER; ++i) {
            System.out.print(board[i] + "ͿɫΪ��" + colors[i]);
            System.out.println();
        }
        return true;
    }

//    public void saveAsExcel(int color[]){
//    }

   // public void readFromExcel(){}

    public static void main(String[] args) {
        String board[]={"����","���","�ӱ�","ɽ��","���ɹ�","����","����","������","�Ϻ�","����","�㽭","����","����","����","ɽ��",
                "����","����","����","�㶫","����","����","����","�Ĵ�","����","����","����","����","����","�ຣ","����","�½�","̨��"};
        // ���ڽӾ�����ͼ�ṹ


        int edge[][] = {
                {0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0},
                {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0},
                {1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0,0},
                {0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 1, 0, 0, 0, 0,0},
                {0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1,0},
                {0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0,0},
                {0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0,0},
                {0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0, 0, 0, 0, 0, 0,0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 1, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0,
                        0, 1, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 1, 0,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0,
                        0, 1, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 1,
                        1, 1, 1, 1, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 0, 1,
                        0, 0, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 1, 0,
                        1, 0, 0, 0, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1,
                        0, 0, 0, 1, 0, 1,0
                }, {0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0,
                        0, 0, 1, 0, 1, 0,0
                }, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
                        0, 1, 0, 1, 1, 1,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0,
                        1, 0, 1, 0, 0, 1,0
                }, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 1, 1, 0, 0, 0,0
                }, {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        1, 0, 1, 1, 0, 0,0
                }, {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                        0, 0, 0, 0, 0, 0,0
                }};
        ChinaMap p = new ChinaMap(board, edge);
        int colorKind = 1;
        while(!p.color(colorKind)) {
            colorKind++;
        }
        for (int i : p.colors) {
            System.out.print(i);
        }

        String[] title = {"id","city","color"};//��������
        //����Excel�ļ�
        String path = "D:\\�й���ͼ��ɫʡ����ɫ������ʱ�ı�.xls";
        File colorChoose = new File(path);
        if (colorChoose.exists()){
            //������ڸ��ļ�����ɾ��
            colorChoose.delete();
        }
        //�����±�
        try {
            colorChoose.createNewFile();
            //����������
            WritableWorkbook workbook = Workbook.createWorkbook(colorChoose);
            //����sheet
            WritableSheet sheet = workbook.createSheet("sheet1",0);
            Label label = null;
            //��������
            for (int i = 0; i < title.length ; i++) {
                label = new Label(i,0,title[i]);
                sheet.addCell(label);
            }
            //��ȡ����Դ
            for (int i = 1; i <=NUMBER; i++) {
                label = new Label(0,i,i+" ");
                sheet.addCell(label);
                label = new Label(1,i,board[i-1]);
                sheet.addCell(label);
                label = new Label(2,i,String.valueOf(p.colors[i-1]));
                sheet.addCell(label);
                }

            workbook.write();//д������
//            workbook.flush();
            workbook.close();//�ر�����

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (RowsExceededException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (WriteException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        System.out.println("����ҪͿ" + colorKind + "����ɫ");
    }

}
