(function ($) {
    $.fn.html5uploader = function (opts) {
        var defaults = {
            fileTypeExts: '',
            url: '',
            auto: false,
            multi: false,
            buttonText: '选择文件',
            removeTimeout: 1000,
            itemTemplate: '<li id="${fileID}file"><div class="progress"><div class="progressbar"></div></div><span class="filename">${fileName}</span><span class="progressnum">0/${fileSize}</span><a class="uploadbtn">上传</a><a class="delfilebtn">删除</a></li>',
            onUploadStart: function () {
            },
            onUploadSuccess: function () {
            },
            onUploadComplete: function () {
            },
            onUploadError: function () {
            },
            onInit: function () {
            },
        }
        var option = $.extend(defaults, opts);
        var formatFileSize = function (size) {
            if (size > 1024 * 1024) {
                size = (Math.round(size * 100 / (1024 * 1024)) / 100).toString() + 'MB';
            }
            else {
                size = (Math.round(size * 100 / 1024) / 100).toString() + 'KB';
            }
            return size;
        }
        var getFile = function (index, files) {
            for (var i = 0; i < files.length; i++) {
                if (files[i].index == index) {
                    return files[i];
                }
            }
            return false;
        }
        var formatFileType = function (str) {
            if (str) {
                return str.split(",");
            }
            return false;
        }
        this.each(function () {
            var _this = $(this);
            var inputstr = '<input class="uploadfile" style="visibility:hidden;" type="file" name="fileselect[]" enctype="multipart/form-data"';
            if (option.multi) {
                inputstr += 'multiple';
            }
            inputstr += '/>';
            inputstr += '<a href="javascript:void(0)" class="uploadfilebtn">';
            inputstr += option.buttonText;
            inputstr += '</a>';
            var fileInputButton = $(inputstr);
            var uploadFileList = $('<ul class="filelist"></ul>');
            _this.append(fileInputButton, uploadFileList);
            var ZXXFILE = {
                fileInput: fileInputButton.get(0),
                upButton: null,
                url: option.url,
                fileFilter: [],
                filter: function (files) {
                    var arr = [];
                    var typeArray = formatFileType(option.fileTypeExts);
                    if (!typeArray) {
                        for (var i in files) {
                            if (files[i].constructor == File) {
                                arr.push(files[i]);
                            }
                        }
                    }
                    else {
                        for (var i in files) {
                            if (files[i].constructor == File) {
                                if ($.inArray(files[i].type, typeArray) >= 0) {
                                    arr.push(files[i]);
                                }
                                else {
                                    alert('文件类型不允许！');
                                    fileInputButton.val('');
                                }
                            }
                        }
                    }
                    return arr;
                },
                onSelect: option.onSelect || function (files) {
                    for (var i = 0; i < files.length; i++) {
                        var file = files[i];
                        var html = option.itemTemplate;
                        html = html.replace(/\${fileID}/g, file.index).replace(/\${fileName}/g, file.name).replace(/\${fileSize}/g, formatFileSize(file.size));
                        uploadFileList.append(html);
                        if (option.auto) {
                            ZXXFILE.funUploadFile(file);
                        }
                    }
                    if (!option.auto) {
                        _this.find('.uploadbtn').die().live('click', function () {
                            var index = parseInt($(this).parents('li').attr('id'));
                            ZXXFILE.funUploadFile(getFile(index, files));
                        });
                    }
                    _this.find('.delfilebtn').live('click', function () {
                        var index = parseInt($(this).parents('li').attr('id'));
                        ZXXFILE.funDeleteFile(index);
                    });
                },
                onDelete: function (index) {
                    //_this.find('#' + index + 'file').remove();
                    $(".filelist").html("");
                },
                onProgress: function (file, loaded, total) {
                    var eleProgress = _this.find('#' + file.index + 'file .progress'),
                        percent = (loaded / total * 100).toFixed(2) + '%';
                    eleProgress.find('.progressbar').css('width', percent);
                    if (total - loaded < 500000) {
                        loaded = total;
                    }
                    eleProgress.parents('li').find('.progressnum').html(formatFileSize(loaded) + '/' + formatFileSize(total));
                },
                onUploadSuccess: option.onUploadSuccess,
                onUploadError: option.onUploadError,
                onUploadComplete: option.onUploadComplete,
                funGetFiles: function (e) {
                    var files = e.target.files || e.dataTransfer.files;
                    files = this.filter(files)
                    this.fileFilter.push(files);
                    this.funDealFiles(files);
                    return this;
                },
                funDealFiles: function (files) {
                    var fileCount = _this.find('.filelist li').length;
                    for (var i = 0; i < this.fileFilter.length; i++) {
                        for (var j = 0; j < this.fileFilter[i].length; j++) {
                            var file = this.fileFilter[i][j];
                            file.index = ++fileCount;
                        }
                    }
                    this.onSelect(files);
                    return this;
                },
                funDeleteFile: function (index) {
                    //_this.html(fileInputButton, uploadFileList);
                    //console.log(this.fileFilter);
                    console.log($("input[name='fileselect[]']"));
                    for (var i = 0; i < this.fileFilter.length; i++) {
                        for (var j = 0; j < this.fileFilter[i].length; j++) {
                            var file = this.fileFilter[i][j];
                            if (file.index == index) {
                                this.fileFilter[i].splice(j, 1);
                                this.onDelete(index);
                            }
                        }
                    }
                    $("#newsCoverImg").val("");
                    $("#imgDiv").remove();
                    $("#upload").html("");
                    _this.append(fileInputButton, uploadFileList);
                    return this;
                },
                funUploadFile: function (file) {
                    var self = this;
                    (function (file) {
                        var xhr = new XMLHttpRequest();
                        if (xhr.upload) {
                            xhr.upload.addEventListener("progress", function (e) {
                                self.onProgress(file, e.loaded, e.total);
                            }, false);
                            xhr.onreadystatechange = function (e) {
                                if (xhr.readyState == 4) {
                                    if (xhr.status == 200) {
                                        $("#newsCoverImg").val(xhr.responseText);
                                        $("#newsCoverImgLabel").append('<div id="imgDiv"><img src="'+xhr.responseText+'"></div>');
                                        self.onUploadSuccess(file, xhr.responseText);
                                        setTimeout(function () {
                                            ZXXFILE.onDelete(file.index);
                                        }, option.removeTimeout);
                                        self.onUploadComplete();
                                    } else {
                                        self.onUploadError(file, xhr.responseText);
                                    }
                                }
                            };
                            var fd = new FormData();
                            fd.append("upload",$("input[name='fileselect[]']")[0].files[0]);
                            option.onUploadStart();
                            xhr.open("POST", self.url, true);
                            xhr.setRequestHeader("X_FILENAME", file.name);
                            xhr.send(fd);
                            //alert(xhr.responseText);
                        }
                    })(file);
                },

                init: function () {
                    var self = this;
                    if (this.fileInput) {
                        this.fileInput.addEventListener("change", function (e) {
                            self.funGetFiles(e);
                        }, false);
                    }
                    _this.find('.uploadfilebtn').live('click', function () {
                        var liList = $(".filelist").find("li");
                        if (liList.size()>0){
                            dialogAlert("提示","只能上传一张封面图！",function () {
                            })
                            return;
                        }
                        _this.find('.uploadfile').trigger('click');
                    });
                    option.onInit();
                }
            };
            ZXXFILE.init();
        });
    }
})(jQuery)