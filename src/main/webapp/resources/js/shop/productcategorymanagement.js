$(function () {
    var listUrl='/o2o/shopadmin/getproductcategorylist';
    var addUrl='/o2o/shopadmin/addproductcategorys';
    var deleteUrl='/o2o/shopadmin/deleteproductcategory';
    getList();
    function getList(){
        $.getJSON(
            listUrl,
            function (data) {
                if(data.success){
                    var dataList=data.data;
                    $('.category-wrap').html('');
                    var tempHtml='';
                    dataList
                        .map(function (item,index) {
                        tempHtml+=''
                        +'<div class="row row-product-category now">'
                        +'<div class="col-33 product-category-name">'
                        +item.productCategoryName
                        +'</div>'
                        +'<div class="col-33">'
                        +item.priority
                        +'</div>'
                        +'<div class="col-33"><a href="#" class="button delete" data-id="'
                        +item.productCategoryId
                        +'">删除</a></div></div>';
                    });
                    $('.category-wrap').append(tempHtml);
                }
            }
        );
    }
    $('#new')
        .click(
            function(){
                var tempHtml='<div class="row row-product-category temp">'
                +'<div class="col-33"><input class="category-input category" type="text" placeholder="分类名"/> </div>'
                +'<div class="col-33"><input class="category-input priority" type="number" placeholder="优先级"/></div>'
                +'<div class="col-33"><a href="#" class="button delete">删除</a></div>'
                +'</div>'
                $('.category-wrap').append(tempHtml);
            }
        );

    $('#submit').click(function () {
        //提交新增部分
        var tempArr=$('.temp');
        var addResult=0;
        var deleteResult=0;
        if(tempArr.length>0) {
            var productCategoryList = [];
            tempArr.map(function (index, item) {
                var tempObj = {};
                tempObj.productCategoryName = $(item).find('.category').val();
                tempObj.priority = $(item).find('.priority').val();
                if (tempObj.productCategoryName && tempObj.priority) {
                    productCategoryList.push(tempObj);
                }
            });
            $.ajax({
                url: addUrl,
                type: 'POST',
                data: JSON.stringify(productCategoryList),
                contentType: 'application/json',
                async:false,
                success: function (data) {
                    if (data.success) {
                        //$.toast('添加成功！');
                         addResult=1;
                    } else {
                        //$.toast('添加失败！');
                        addResult=-1;
                    }
                }
            });
        }

        //提交删除部分
        var tempArr=$('.deleted');
        if(tempArr.length>0) {
            var productCategoryList = [];
            tempArr.map(function (index, item) {
                var tempObj = {};
                console.log($(item).find('.button-light'));
                tempObj.productCategoryId = $(item).find('.button-light').attr('data-id');
                if (tempObj.productCategoryId) {
                    productCategoryList.push(tempObj);
                }
            });

            $.ajax({
                url: deleteUrl,
                type: 'Post',
                data: JSON.stringify(productCategoryList),
                contentType: 'application/json',
                async:false,
                success: function (data) {
                    if (data.success) {
                        //$.toast('删除成功！');
                        deleteResult=1;
                    } else {
                        //$.toast('删除失败！');
                        deleteResult=-1;
                    }
                }
            });
        }
        if(addResult==1&&deleteResult==0)
            $.toast('添加成功！');
        else if(addResult==0&&deleteResult==1)
            $.toast('删除成功！');
        else if(addResult==1&&deleteResult==-1)
            $.toast('添加成功！删除失败！');
        else if(addResult==-1&&deleteResult==1)
            $.toast('添加失败！删除成功！');
        else if(addResult==1&&deleteResult==1)
            $.toast('添加成功！删除成功！');
        getList();
    //     setTimeout(function(){
    //         getList();} ,800);
    });

    $('.category-wrap').on('click', '.row-product-category.temp .delete',
        function (e) {
            console.log($(this).parent().parent());//控制台显示
            $(this).parent().parent().remove();
        });

    $('.category-wrap').on('click', '.row-product-category.now .delete',
        function (e) {
            console.log( $(this).parent().siblings());//控制台显示
            $(this).parent().siblings().css("text-decoration","line-through");
            $(this).text("已删除");
            $(this).prop("class","button button-light");
            $(this).parent().parent().prop('class','row row-product-category deleted');
            console.log( $(this).parent().parent())
        });
});