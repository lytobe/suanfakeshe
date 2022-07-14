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
    //初始化所有顶点均涂色0
    private int[] colors = new int[NUMBER];

    public ChinaMap(String[] board, int[][] edge) {
        this.board = board;
        this.edge = edge;
    }

    public boolean color(int colorKind) {
        int cityNode = 0;    //从图中的一个省开始
        //cityNode从0~BOARD_NUMBER-1表示所有省
        while(cityNode <NUMBER) {
            int flag = 1;//结束while循环的标志
            //如果发现colorKind种颜色不能满足，返回false
            if(cityNode == -1) {
                return false;
            }
            colors[cityNode]++;//color[cityNode]+1,此处是为统计加号出现的次数，即现有多少种颜色
            //当涂色超过colorKind种，回溯，及回到上一个索引，然后这个城市节点颜色数重置为0，重新运算
            if(colors[cityNode] > colorKind) {
                colors[cityNode] = 0;
                cityNode--;
                continue;
            }
            for(int i = 0; i < cityNode; ++i) {
                //发现邻接点涂相同颜色
                if (edge[cityNode][i] == 1 && colors[cityNode] == colors[i]) {
                    flag = 0;
                    break;
                }
            }
            //发现邻接点涂相同颜色,该结点重涂
            if(flag == 0) {
                continue;
            }
            cityNode++;//如符合要求则涂下一个城市节点
        }
        for(int i = 0; i <NUMBER; ++i) {
            System.out.print(board[i] + "涂色为：" + colors[i]);
            System.out.println();
        }
        return true;
    }

//    public void saveAsExcel(int color[]){
//    }

   // public void readFromExcel(){}

    public static void main(String[] args) {
        String board[]={"北京","天津","河北","山西","内蒙古","辽宁","吉林","黑龙江","上海","江苏","浙江","安徽","福建","江西","山东",
                "河南","湖北","湖南","广东","广西","海南","重庆","四川","贵州","云南","西藏","陕西","甘肃","青海","宁夏","新疆","台湾"};
        // 用邻接矩阵建立图结构


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

        String[] title = {"id","city","color"};//创建列名
        //创建Excel文件
        String path = "D:\\中国地图着色省市颜色数据临时文本.xls";
        File colorChoose = new File(path);
        if (colorChoose.exists()){
            //如果存在该文件，就删除
            colorChoose.delete();
        }
        //创建新表
        try {
            colorChoose.createNewFile();
            //创建工作簿
            WritableWorkbook workbook = Workbook.createWorkbook(colorChoose);
            //创建sheet
            WritableSheet sheet = workbook.createSheet("sheet1",0);
            Label label = null;
            //设置列名
            for (int i = 0; i < title.length ; i++) {
                label = new Label(i,0,title[i]);
                sheet.addCell(label);
            }
            //获取数据源
            for (int i = 1; i <=NUMBER; i++) {
                label = new Label(0,i,i+" ");
                sheet.addCell(label);
                label = new Label(1,i,board[i-1]);
                sheet.addCell(label);
                label = new Label(2,i,String.valueOf(p.colors[i-1]));
                sheet.addCell(label);
                }

            workbook.write();//写入数据
//            workbook.flush();
            workbook.close();//关闭连接

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

        System.out.println("最少要涂" + colorKind + "种颜色");
    }

}
