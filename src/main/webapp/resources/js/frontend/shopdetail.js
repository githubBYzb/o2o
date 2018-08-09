$(function(){

    var loading = false;
    var maxItems = 999;
    var pageSize = 3;
    var listUrl = '/o2o/frontend/listproductsbyshop';
    var pageNum = 1;
    var shopId = getQueryString('shopId');
    var productCategoryId = '';
    var productName = '';
    var searchDivUrl = '/o2o/frontend/listshopdetailpageinfo?shopId=' + shopId;
    getSearchDivData();
    addItems(pageSize,pageNum);

    function jsonDateFormat(jsonDate) {//json日期格式转换为正常格式
        if(jsonDate == null) {
            return "";
        }
        try {
            var date = new Date(parseInt(jsonDate, 10));
            var year = date.getFullYear()
            var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
            var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
            return year + "-" + month + "-" + day ;
        } catch (ex) {
            return "";
        }
    }
    // $('#exchangelist').attr('href','/o2o/frontend/awardlist?shopId=' + shopId);
    function getSearchDivData() {
        var url=searchDivUrl;
        $
            .getJSON(
                url,
                function (data){
                    if(data.success){
                        var shop=data.shop;
                        $('#shop-cover-pic').attr('src',shop.shopImg);
                        $('#shop-update-time').html(
                            jsonDateFormat(shop.lastEditTime));
                        $('#shop-name').html(shop.shopName);
                        $('#shop-desc').html(shop.shopDesc);
                        $('#shop-addr').html(shop.shopAddr);
                        $('#shop-phone').html(shop.phone);
                        //获取后台返回的该店铺的商品类别列表
                        var productCategoryList = data.productCategoryList;
                        var html ='';
                        productCategoryList
                            .map(function(item,index){
                                html +='<a href="#" class="button" data-product-shopid='
                                    + item.productCategoryId
                                    +'>'
                                    + item.productCategoryName
                                    +'</a>';
                            });
                        $('#shopdetail-button-div').html(html);
                    }
                });
    }
    function addItems(pageSize,pageIndex){
        var url = listUrl + '?' + 'pageIndex=' + pageIndex + '&pageSize='
            + pageSize + '&productCategoryId=' + productCategoryId
            +'&productName='+ productName + '&shopId=' + shopId;
        loading = true;
        $.getJSON(url,function(data){
            if(data.success){
                maxItems = data.count;

                var html = '';
                data.productList.map(function(item,index){
                    html+= '' +'<div class="card" data-product-id='
                        + item.productId + '>' + '<div class="card-header">'
                        + item.productName+'</div>'
                        + '<div class="card-content">'
                        + '<div class="list-block media-list">' + '<ul>'
                        + '<li class="item-content">'
                        + '<div class="item-media">' + '<img src="'
                        + item.imgAddr + '"width="44">' + '</div>'
                        + '<div class="item-inner">'
                        + '<div class=:item-subtitle">' + item.productDesc
                        + '</div>' + '</div>' + '</li>' + '</ul>' + '</div>' + '</div>'
                        + '<div class="card-footer">' +'<p class="color-gray">'
                        + jsonDateFormat(item.lastEditTime)
                        + '更新</p>' + '<span>点击查看</span>' + '</div>'
                        +'</div>';
                });
                $('.list-div').append(html);
                var total = $('.list-div .card').length;
                if(total>=maxItems){
                    $('.infinite-scroll-preloader').hide();
                }else{
                    $('.infinite-scroll-preloader').show();
                }
                pageNum += 1;
                loading = false;
                $.refreshScroller();
            }
        });
    }

    $(document).on('infinite','.infinite-scroll-bottom',function(){
        if(loading)
            return;
        addItems(pageSize, pageNum);
    });
    $('#shopdetail-button-div').on(
        'click',
        '.button',
        function(e){
            productCategoryId = e.target.dataset.productSearchId;
            if(productCategoryId){
                if($(e.target).hasClass('button-fill')){
                    $(e.target).removeClass('button-fill');
                    productCategoryId = '';
                }else{
                    $(e.target).addClass('button-fill').siblings()
                        .removeClass('button-fill');
                }
                $('.list-div').empty();
                pageNum = 1;
                addItems(pageSize, pageNum);
            }
        });
    $('.list-div').on(
        'click',
        '.card',
        function(e){
            var productId = e.currentTarget.dataset.productId;
            window.location.href = '/o2o/frontend/productdetail?productId='
                +productId;
        });
    $('#search').on('change',function(e){
        productName = e.target.value;
        $('list-div').empty();
        pageNum = 1;
        addItems(pageSize,pageNum);
    });
    $('#me').click(function(){
        $.openPanel('#panel-right-demo');
    });
    $.init();
});
               