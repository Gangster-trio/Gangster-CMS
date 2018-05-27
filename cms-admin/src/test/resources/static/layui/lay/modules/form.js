/** layui-v2.2.5 MIT License By https://www.layui.com */
;layui.define("layer", function (e) {
    "use strict";
    var t = layui.$, i = layui.layer, a = layui.hint(), n = layui.device(), l = "form", r = ".layui-form",
        s = "layui-this", o = "layui-hide", u = "layui-disabled", c = function () {
            this.config = {
                verify: {
                    required: [/[\S]+/, "必填项不能为空"],
                    phone: [/^1\d{10}$/, "请输入正确的手机号"],
                    email: [/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/, "邮箱格式不正确"],
                    url: [/(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/, "链接格式不正确"],
                    number: function (e) {
                        if (!e || isNaN(e)) return "只能填写数字"
                    },
                    date: [/^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/, "日期格式不正确"],
                    identity: [/(^\d{15}$)|(^\d{17}(x|X|\d)$)/, "请输入正确的身份证号"]
                }
            }
        };
    c.prototype.set = function (e) {
        var i = this;
        return t.extend(!0, i.config, e), i
    }, c.prototype.verify = function (e) {
        var i = this;
        return t.extend(!0, i.config.verify, e), i
    }, c.prototype.on = function (e, t) {
        return layui.onevent.call(this, l, e, t)
    }, c.prototype.render = function (e, i) {
        var n = this, c = t(r + function () {
            return i ? '[lay-filter="' + i + '"]' : ""
        }()), d = {
            select: function () {
                var TIPS = '请选择', CLASS = 'layui-form-select', TITLE = 'layui-select-title'
                    , NONE = 'layui-select-none', initValue = '', thatInput
                    , selects = elemForm.find('select'), hide = function (e, clear) {
                    if (!$(e.target).parent().hasClass(TITLE) || clear) {
                        $('.' + CLASS).removeClass(CLASS + 'ed ' + CLASS + 'up');


                        if (typeof $(thatInput).attr('lay-autocomplete') === 'string') {//Marje，判断是否有lay-autocomplete属性
                            thatInput && initValue && thatInput.val();//有则不给thatInput赋值，使用自身的值
                        } else {
                            thatInput && initValue && thatInput.val(initValue);
                        }


                    }
                    thatInput = null;
                }

                    , events = function (reElem, disabled, isSearch, isAutoComplete) {//Marje
                    var select = $(this)
                        , title = reElem.find('.' + TITLE)
                        , input = title.find('input')
                        , dl = reElem.find('dl')
                        , dds = dl.children('dd')


                    if (disabled) return;

                    //展开下拉
                    var showDown = function () {
                        var top = reElem.offset().top + reElem.outerHeight() + 5 - win.scrollTop()
                            , dlHeight = dl.outerHeight();
                        reElem.addClass(CLASS + 'ed');
                        dds.removeClass(HIDE);

                        //上下定位识别
                        if (top + dlHeight > win.height() && top >= dlHeight) {
                            reElem.addClass(CLASS + 'up');
                        }
                    }, hideDown = function (choose) {
                        reElem.removeClass(CLASS + 'ed ' + CLASS + 'up');
                        input.blur();
                        if (choose) return;
                        notOption(input.val(), function (none) {
                            if (none) {
                                initValue = dl.find('.' + THIS).html();
                                if (isAutoComplete) {//Marje
                                    input && input.val();
                                } else {
                                    input && input.val(initValue);
                                }
                            }
                        });

                    };

                    //点击标题区域
                    title.on('click', function (e) {
                        reElem.hasClass(CLASS + 'ed') ? (
                            hideDown()
                        ) : (
                            hide(e, true),
                                showDown()
                        );
                        dl.find('.' + NONE).remove();
                    });

                    //点击箭头获取焦点
                    title.find('.layui-edge').on('click', function () {
                        input.focus();
                    });

                    //键盘事件
                    input.on('keyup', function (e) {
                        var keyCode = e.keyCode;
                        //Tab键
                        if (keyCode === 9) {
                            showDown();
                        }
                    }).on('keydown', function (e) {
                        var keyCode = e.keyCode;
                        //Tab键
                        if (keyCode === 9) {
                            hideDown();
                        } else if (keyCode === 13) { //回车键
                            e.preventDefault();
                        }
                    });

                    //检测值是否不属于select项
                    var notOption = function (value, callback, origin) {
                        var num = 0;
                        layui.each(dds, function () {
                            var othis = $(this)
                                , text = othis.text()
                                , not = text.indexOf(value) === -1;
                            if (value === '' || (origin === 'blur') ? value !== text : not) num++;
                            origin === 'keyup' && othis[not ? 'addClass' : 'removeClass'](HIDE);
                        });
                        var none = num === dds.length;
                        return callback(none), none;
                    };

                    //搜索匹配
                    var search = function (e) {
                        var value = this.value, keyCode = e.keyCode;

                        if (keyCode === 9 || keyCode === 13
                            || keyCode === 37 || keyCode === 38
                            || keyCode === 39 || keyCode === 40
                        ) {
                            return false;
                        }

                        notOption(value, function (none) {
                            if (none) {
                                dl.find('.' + NONE)[0] || dl.append('<p class="' + NONE + '">无匹配项</p>');
                            } else {
                                dl.find('.' + NONE).remove();
                            }
                        }, 'keyup');

                        if (value === '') {
                            dl.find('.' + NONE).remove();
                        }
                    };

                    if (isSearch) {
                        input.on('keyup', search).on('blur', function (e) {
                            thatInput = input;
                            initValue = dl.find('.' + THIS).html();
                            setTimeout(function () {
                                notOption(input.val(), function (none) {
                                    if (isAutoComplete) {//Marje
                                        input.val();
                                    } else {
                                        initValue || input.val(''); //none && !initValue
                                    }
                                }, 'blur');
                            }, 200);
                        });
                    }

                    //选择
                    dds.on('click', function () {
                        var othis = $(this), value = othis.attr('lay-value');
                        var filter = select.attr('lay-filter'); //获取过滤器

                        if (othis.hasClass(DISABLED)) return false;

                        if (othis.hasClass('layui-select-tips')) {
                            input.val('');
                        } else {
                            input.val(othis.text());
                            othis.addClass(THIS);
                        }

                        othis.siblings().removeClass(THIS);
                        select.val(value).removeClass('layui-form-danger')
                        layui.event.call(this, MOD_NAME, 'select(' + filter + ')', {
                            elem: select[0]
                            , value: value
                            , othis: reElem
                        });

                        hideDown(true);
                        return false;
                    });

                    reElem.find('dl>dt').on('click', function (e) {
                        return false;
                    });

                    //关闭下拉
                    $(document).off('click', hide).on('click', hide);
                }

                selects.each(function (index, select) {
                    var othis = $(this)
                        , hasRender = othis.next('.' + CLASS)
                        , disabled = this.disabled
                        , value = select.value
                        , selected = $(select.options[select.selectedIndex]) //获取当前选中项
                        , optionsFirst = select.options[0];

                    if (typeof othis.attr('lay-ignore') === 'string') return othis.show();


                    var isAutoComplete = typeof othis.attr('lay-autocomplete') === 'string',//Marje，增加属性autocomplete
                        elemId = othis.attr('id'), elemName = othis.attr('name');
                    if (isAutoComplete) {//将移除select的id和name属性，将这两个属性移至input上
                        othis.removeAttr("id");
                        othis.removeAttr("name");
                    }


                    var isSearch = typeof othis.attr('lay-search') === 'string'
                        , placeholder = optionsFirst ? (
                        optionsFirst.value ? TIPS : (optionsFirst.innerHTML || TIPS)
                    ) : TIPS;

                    //替代元素
                    var reElem = $(['<div class="' + (isSearch ? '' : 'layui-unselect ') + CLASS + (disabled ? ' layui-select-disabled' : '') + '">'
                        //Marje
                        , '<div class="' + TITLE + '"><input type="text" id="' + (isAutoComplete ? elemId : "") + '" name="' + (isAutoComplete ? elemName : "") + '" ' + (isAutoComplete ? "lay-autocomplete" : "") + '  placeholder="' + placeholder + '" value="' + (value ? selected.html() : "") + '" ' + (isSearch ? '' : 'readonly') + ' class="layui-input' + (isSearch ? '' : ' layui-unselect') + (disabled ? (' ' + DISABLED) : '') + '">'
                        , '<i class="layui-edge"></i></div>'
                        , '<dl class="layui-anim layui-anim-upbit' + (othis.find('optgroup')[0] ? ' layui-select-group' : '') + '">' + function (options) {
                            var arr = [];
                            layui.each(options, function (index, item) {
                                if (index === 0 && !item.value) {
                                    arr.push('<dd lay-value="" class="layui-select-tips">' + (item.innerHTML || TIPS) + '</dd>');
                                } else if (item.tagName.toLowerCase() === 'optgroup') {
                                    arr.push('<dt>' + item.label + '</dt>');
                                } else {
                                    arr.push('<dd lay-value="' + item.value + '" class="' + (value === item.value ? THIS : '') + (item.disabled ? (' ' + DISABLED) : '') + '">' + item.innerHTML + '</dd>');
                                }
                            });
                            arr.length === 0 && arr.push('<dd lay-value="" class="' + DISABLED + '">没有选项</dd>');
                            return arr.join('');
                        }(othis.find('*')) + '</dl>'
                        , '</div>'].join(''));

                    hasRender[0] && hasRender.remove(); //如果已经渲染，则Rerender
                    othis.after(reElem);
                    events.call(this, reElem, disabled, isSearch, isAutoComplete);//Marje
                });
            }

            , checkbox: function () {
                var e = {
                    checkbox: ["layui-form-checkbox", "layui-form-checked", "checkbox"],
                    _switch: ["layui-form-switch", "layui-form-onswitch", "switch"]
                }, i = c.find("input[type=checkbox]"), a = function (e, i) {
                    var a = t(this);
                    e.on("click", function () {
                        var t = a.attr("lay-filter"), n = (a.attr("lay-text") || "").split("|");
                        a[0].disabled || (a[0].checked ? (a[0].checked = !1, e.removeClass(i[1]).find("em").text(n[1])) : (a[0].checked = !0, e.addClass(i[1]).find("em").text(n[0])), layui.event.call(a[0], l, i[2] + "(" + t + ")", {
                            elem: a[0],
                            value: a[0].value,
                            othis: e
                        }))
                    })
                };
                i.each(function (i, n) {
                    var l = t(this), r = l.attr("lay-skin"), s = (l.attr("lay-text") || "").split("|"),
                        o = this.disabled;
                    "switch" === r && (r = "_" + r);
                    var c = e[r] || e.checkbox;
                    if ("string" == typeof l.attr("lay-ignore")) return l.show();
                    var d = l.next("." + c[0]),
                        f = t(['<div class="layui-unselect ' + c[0] + (n.checked ? " " + c[1] : "") + (o ? " layui-checkbox-disbaled " + u : "") + '" lay-skin="' + (r || "") + '">', {_switch: "<em>" + ((n.checked ? s[0] : s[1]) || "") + "</em><i></i>"}[r] || (n.title.replace(/\s/g, "") ? "<span>" + n.title + "</span>" : "") + '<i class="layui-icon">' + (r ? "&#xe605;" : "&#xe618;") + "</i>", "</div>"].join(""));
                    d[0] && d.remove(), l.after(f), a.call(this, f, c)
                })
            }, radio: function () {
                var e = "layui-form-radio", i = ["&#xe643;", "&#xe63f;"], a = c.find("input[type=radio]"),
                    n = function (a) {
                        var n = t(this), s = "layui-anim-scaleSpring";
                        a.on("click", function () {
                            var o = n[0].name, u = n.parents(r), c = n.attr("lay-filter"),
                                d = u.find("input[name=" + o.replace(/(\.|#|\[|\])/g, "\\$1") + "]");
                            n[0].disabled || (layui.each(d, function () {
                                var a = t(this).next("." + e);
                                this.checked = !1, a.removeClass(e + "ed"), a.find(".layui-icon").removeClass(s).html(i[1])
                            }), n[0].checked = !0, a.addClass(e + "ed"), a.find(".layui-icon").addClass(s).html(i[0]), layui.event.call(n[0], l, "radio(" + c + ")", {
                                elem: n[0],
                                value: n[0].value,
                                othis: a
                            }))
                        })
                    };
                a.each(function (a, l) {
                    var r = t(this), s = r.next("." + e), o = this.disabled;
                    if ("string" == typeof r.attr("lay-ignore")) return r.show();
                    s[0] && s.remove();
                    var c = t(['<div class="layui-unselect ' + e + (l.checked ? " " + e + "ed" : "") + (o ? " layui-radio-disbaled " + u : "") + '">', '<i class="layui-anim layui-icon">' + i[l.checked ? 0 : 1] + "</i>", "<div>" + function () {
                        var e = l.title || "";
                        return "string" == typeof r.next().attr("lay-radio") && (e = r.next().html(), r.next().remove()), e
                    }() + "</div>", "</div>"].join(""));
                    r.after(c), n.call(this, c)
                })
            }
        };
        return e ? d[e] ? d[e]() : a.error("不支持的" + e + "表单渲染") : layui.each(d, function (e, t) {
            t()
        }), n
    };
    var d = function () {
        var e = t(this), a = f.config.verify, s = null, o = "layui-form-danger", u = {}, c = e.parents(r),
            d = c.find("*[lay-verify]"), y = e.parents("form")[0], v = c.find("input,select,textarea"),
            h = e.attr("lay-filter");
        if (layui.each(d, function (e, l) {
                var r = t(this), u = r.attr("lay-verify").split("|"), c = r.attr("lay-verType"), d = r.val();
                if (r.removeClass(o), layui.each(u, function (e, t) {
                        var u, f = "", y = "function" == typeof a[t];
                        if (a[t]) {
                            var u = y ? f = a[t](d, l) : !a[t][0].test(d);
                            if (f = f || a[t][1], u) return "tips" === c ? i.tips(f, function () {
                                return "string" == typeof r.attr("lay-ignore") || "select" !== l.tagName.toLowerCase() && !/^checkbox|radio$/.test(l.type) ? r : r.next()
                            }(), {tips: 1}) : "alert" === c ? i.alert(f, {
                                title: "提示",
                                shadeClose: !0
                            }) : i.msg(f, {icon: 5, shift: 6}), n.android || n.ios || l.focus(), r.addClass(o), s = !0
                        }
                    }), s) return s
            }), s) return !1;
        var p = {};
        return layui.each(v, function (e, t) {
            if (t.name = (t.name || "").replace(/^\s*|\s*&/, ""), t.name) {
                if (/^.*\[\]$/.test(t.name)) {
                    var i = t.name.match(/^(.*)\[\]$/g)[0];
                    p[i] = 0 | p[i], t.name = t.name.replace(/^(.*)\[\]$/, "$1[" + p[i]++ + "]")
                }
                /^checkbox|radio$/.test(t.type) && !t.checked || (u[t.name] = t.value)
            }
        }), layui.event.call(this, l, "submit(" + h + ")", {elem: this, form: y, field: u})
    }, f = new c, y = t(document), v = t(window);
    f.render(), y.on("reset", r, function () {
        var e = t(this).attr("lay-filter");
        setTimeout(function () {
            f.render(null, e)
        }, 50)
    }), y.on("submit", r, d).on("click", "*[lay-submit]", d), e(l, f)
});