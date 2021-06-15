window.addEventListener("DOMContentLoaded", function () {
    downloadScript([
        'https://unpkg.com/xlsx/dist/xlsx.full.min.js',
    ], function () {
        console.log("不小心成功了");
    }, function (errUrl) {
        console.log(errUrl);
    });
    // 下载js方法start
    // 批量下载解析script
    function downloadScript(scriptUrlArr, callback, errorback) {
        var downloadCount = 0;
        var isError = false;

        var i = 0, length = scriptUrlArr.length;
        for (i; i < length; i++) {
            addScript(scriptUrlArr[i], startCallback, startErrorback)
        }

        function startCallback() {
            if (isError) return;
            downloadCount++;
            if (downloadCount == length) {
                callback && callback();
            }
        }

        function startErrorback(scriptUrl) {
            isError = true;
            var errorMsg = scriptUrl + '链接无效';
            console.error(errorMsg)
            errorback && errorback(errorMsg);
        }
    }

    // 添加script下载
    function addScript(scriptUrl, callback, errorback) {
        var script = document.createElement("script");
        script.src = scriptUrl;
        document.body.appendChild(script);
        document.body.removeChild(script);
        script.onload = function () {
            callback && callback();
        }
        script.onerror = function () {
            errorback && errorback(scriptUrl);
        }

    }

    // 下载js方法end

    // 上传方法 start
    // 上传excel方法
    function uploadXls(dom, callback, errorback) {
        var file = document.createElement("input");
        file.type = 'file';
        // file.multiple = "multiple";  //批量
        file.style.display = 'none';
        document.body.appendChild(file);
        file.onchange = function (e) {
            var files = e.target.files;
            // var files = file.files;
            if (files.length == 0) {
                // errorback && errorback("没有上传文件");
                return;
            }
            var f = files[0];
            if (!/\.xlsx$/g.test(f.name) && !/\.xls$/g.test(f.name)) {
                errorback && errorback('仅支持读取xlsx和xls格式！');
                return;
            }
            callback & callback(f);
        };

        dom.onclick = function () {
            file.click();
        }
    }

    // 将excel的binary数据解析成JSON
    function readWorkbookFromLocalFile(file, callback) {
        var reader = new FileReader();
        reader.onload = function (e) {
            var data = e.target.result;
            var workbook = XLSX.read(data, {type: 'binary'});
            // var json = JSON.stringify( XLSX.utils.sheet_to_json(workbook.Sheets[workbook.SheetNames[0]]) );
            var json = XLSX.utils.sheet_to_json(workbook.Sheets[workbook.SheetNames[0]]);
            if (callback) callback(json);
        };
        reader.readAsBinaryString(file);
    }

    // 上传方法 end


    // 下载excel方法 start
    function downloadExl(data, type) {
        const wopts = {bookType: 'xlsx', bookSST: false, type: 'binary'};//这里的数据是用来定义导出的格式类型
        // const wopts = { bookType: 'csv', bookSST: false, type: 'binary' };//ods格式
        // const wopts = { bookType: 'ods', bookSST: false, type: 'binary' };//ods格式
        // const wopts = { bookType: 'xlsb', bookSST: false, type: 'binary' };//xlsb格式
        // const wopts = { bookType: 'fods', bookSST: false, type: 'binary' };//fods格式
        // const wopts = { bookType: 'biff2', bookSST: false, type: 'binary' };//xls格式

        var wb = {SheetNames: ['Sheet1'], Sheets: {}, Props: {}};
        wb.Sheets['Sheet1'] = XLSX.utils.json_to_sheet(data);//通过json_to_sheet转成单页(Sheet)数据
        saveAs(new Blob([s2ab(XLSX.write(wb, wopts))], {type: "application/octet-stream"}), "这里是下载的文件名" + '.' + (wopts.bookType == "biff2" ? "xls" : wopts.bookType));
    }

    function s2ab(s) {
        if (typeof ArrayBuffer !== 'undefined') {
            var buf = new ArrayBuffer(s.length);
            var view = new Uint8Array(buf);
            for (var i = 0; i != s.length; ++i) view[i] = s.charCodeAt(i) & 0xFF;
            return buf;
        } else {
            var buf = new Array(s.length);
            for (var i = 0; i != s.length; ++i) buf[i] = s.charCodeAt(i) & 0xFF;
            return buf;
        }
    }

    //如果不能下载，那么清注释saveAs方法，第八行恢复FileSaver.js的引入
    function saveAs(obj, fileName) {    //当然可以自定义简单的下载文件实现方式
        var hrefDom = document.createElement("a");
        hrefDom.download = fileName || '下载.xls';
        hrefDom.href = URL.createObjectURL(obj);    //绑定a标签
        hrefDom.style.display = "none";
        document.body.appendChild(hrefDom);
        hrefDom.click(); //模拟点击实现下载
        document.body.removeChild(hrefDom);
        var set = setTimeout(function () { //延时释放
            URL.revokeObjectURL(obj); //用URL.revokeObjectURL()来释放这个object URL
            clearTimeout(set);  //释放内存
            set = null;
        }, 100);
    }

    // 下载excel方法 end


    // util start
    /*
    * 将json数据转成sheet数据
    * */
    function toSheet(params) {
        if (!params) return [];
        var sheetName = params.sheetName;
        var datas = params.datas;

        var i = 0, length = datas.length, data;
        var _datas = [];
        for (i; i < length; i++) {
            data = datas[i];
            var key;
            var _data = {}
            for (key in sheetName) {
                _data[sheetName[key]] = data[key]
            }
            _datas.push(_data);
        }
        return _datas;
    }
    /*
    * 将sheet数据转成json数据
    * */
    function toJson(params) {
        if (!params) return [];
        var sheetName = params.sheetName;
        var datas = params.datas;

        var i = 0, length = datas.length, data;
        var _datas = [];
        for (i; i < length; i++) {
            data = datas[i];
            var key;
            var _data = {}
            for (key in sheetName) {
                _data[key] = data[sheetName[key]]
            }
            _datas.push(_data);
        }
        return _datas;
    }

    // util end

    window.sheetJson = {
        // downloadScript: downloadScript,
        // addScript: addScript,
        uploadXls: uploadXls,
        readWorkbookFromLocalFile: readWorkbookFromLocalFile,
        downloadExl: downloadExl,
        // s2ab: s2ab,
        // saveAs: saveAs,
        toSheet: toSheet,
        toJson: toJson
    };
})