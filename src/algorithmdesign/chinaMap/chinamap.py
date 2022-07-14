from pyecharts.charts import Map
from pyecharts import options as opts
import pandas as pd
import numpy as np


data = pd.read_excel('D:\中国地图着色省市颜色数据临时文本.xls')
# data.render("d.html")





c = (
    Map()
    .add(
        "city",
        [list(z) for z in zip(data['city'],data['color'])],
        maptype = 'china',
        is_map_symbol_show=False,#不显示小红点
    )
    .set_global_opts(
        title_opts=opts.TitleOpts(
            title='地图',
            subtitle='.',
            pos_right='center',
            pos_top='5%'
        ),
        legend_opts=opts.LegendOpts(is_show=False),#图例是否显示
        visualmap_opts=opts.VisualMapOpts(
            max_=4,
            min_=0,
            is_piecewise=True,#是否为分段值
            split_number=4,#分多少段
            range_color=["#ffb75e","#ED8F03","#8E0E00","#76b852"],
        ),

    ))
c.render("中国地图.html")

