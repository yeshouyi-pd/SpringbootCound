<template>
    <div>
        <h3 class="lighter">
            <i class="ace-icon fa fa-hand-o-right icon-animated-hand-pointer blue"></i>
            <router-link to="/business/course" data-toggle="modal" class="blue">{{course.name}}</router-link>
        </h3>

        <div class="hr hr-18 hr-double dotted"></div>
        <p>
            <router-link to="/business/course" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-arrow-left "></i>
                返回课程
            </router-link>
            <button @click="add()" class="btn btn-white btn-default btn-round">
                <i class="ace-icon fa fa-edit "></i>
                新增
            </button>
            &nbsp;
            <button @click="list()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-refresh "></i>
            刷新
        </button>
        </p>
        <pagination  ref="pagination" v-bind:list="list" v-bind:itemCount="8"></pagination>
        <table id="simple-table" class="table  table-bordered table-hover">
            <thead>
            <tr>
                <th >ID</th>
                <th >名称</th>
                <th>操作</th>
            </tr>
            </thead>

            <tbody>
            <tr v-for="chapter in chapters">
                <td >{{chapter.id}}</td>
                <td >{{chapter.name}}</td>


                <td>
                    <div class="hidden-sm hidden-xs btn-group">

                        <button v-on:click="toSection(chapter)" class="btn btn-white btn-xs btn-info  btn-round">
                            小节
                        </button>

                        <button v-on:click="edit(chapter)" class="btn btn-white btn-xs btn-info btn-round">
                            编辑
                        </button>
                        <button v-on:click="del(chapter.id)" class="btn btn-white  btn-xs btn-danger btn-round">
                            删除
                        </button>


                    </div>

                    <div class="hidden-md hidden-lg">
                        <div class="inline pos-rel">
                            <button class="btn btn-minier btn-primary dropdown-toggle" data-toggle="dropdown"
                                    data-position="auto">
                                <i class="ace-icon fa fa-cog icon-only bigger-110"></i>
                            </button>

                        </div>
                    </div>
                </td>
            </tr>



            </tbody>
        </table>
        <div  id = "from-modal" class="modal fade" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">表单</h4>
                    </div>
                    <div class="modal-body">
                       <!-- 模态框里面的表单-->
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">名称</label>
                                <div class="col-sm-10">
                                    <input     v-model="chapter.name" type="text" class="form-control"  >
                                </div>
                            </div>
                            <div class="form-group">
                                <label  class="col-sm-2 control-label">课程</label>
                                <div class="col-sm-10">
                                    <p class="form-control-static"> {{course.name}}</p>
                                </div>
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" @click="save()" class="btn btn-primary">保存</button>
                    </div>
                </div><!-- /.modal-content -->
            </div><!-- /.modal-dialog -->
        </div><!-- /.modal -->

    </div>
</template>
<script>
    import Pagination from "../../components/pagination";
    export default {
        name:'chapter',
        components: {Pagination},
        data:function(){
          return{
              chapter:{},
              chapters:[],
              course:{}
          }
        },
        mounted:function(){//mounted初始化方法

            let _this = this;
            let  course = SessionStorage.get(SESSION_KEY_COURSE)|| {};
            if(Tool.isEmpty(course)){
                _this.$router.push("/welcome");
            }
            _this.course = course;
            _this.list(1);//页面初始化执行
            this.$parent.activeSidebae("business-course-sidebar");
        },
        methods:{
            add(){
              let _this = this;
                this.chapter={};
              $("#from-modal").modal("show");//点开模态框
            },
            edit(chapter){
                let _this = this;
                // this.chapter=chapter;
                //解决VUE双向绑定的问题
                this.chapter=$.extend({},chapter);
                $("#from-modal").modal("show");//点开模态框
            },
            toSection(chapter){
                let _this = this;
                SessionStorage.set(SESSION_KEY_CHAPTER,chapter); //写入缓存
                _this.$router.push("/business/section");
            },

            del(id){
                let _this = this;
                Confirm.show("请谨慎操作!",function () {
                    Loading.show();
                    _this.$ajax.delete(process.env.VUE_APP_SERVER +'/business/admin/chapter/delete/'+id).then((respones)=>{
                        console.log("保存大章结果：", respones);
                        Loading.hide();
                        let resp = respones.data;
                        if(resp.success){
                            _this.list(1);
                            Toast.success("删除成功！")
                        }
                    })
                })
            },
            list(page){
               let _this = this;
                Loading.show();
               _this.$ajax.post(process.env.VUE_APP_SERVER +'/business/admin/chapter/list',{
                   page:page,
                   size:_this.$refs.pagination.size,
                   courseId:_this.course.id,
               })
                   .then((respones)=>{
                       Loading.hide();
                       console.log("查询大章结果：", respones);
                      let resp = respones.data;
                       _this.chapters = resp.content.list;
                       _this.$refs.pagination.render(page,resp.content.total)

                   })
           },
            save(page){
                let _this = this;

                //保存校验
                if (!Validator.require(_this.chapter.name, "名称")
                    || !Validator.length(_this.chapter.courseId, "课程ID", 1, 8)
                    ) {
                    return;
                }
                _this.chapter.courseId=_this.course.id;
                Loading.show();
                _this.$ajax.post(process.env.VUE_APP_SERVER +'/business/admin/chapter/save',
                    _this.chapter).then((respones)=>{
                    Loading.hide();
                        console.log("保存大章结果：", respones);
                    let resp = respones.data;
                    if(resp.success){
                        $("#from-modal").modal("hide");//点开模态框
                        _this.list(1);
                        Toast.success("保存成功！")
                    }else{
                        Toast.warning(resp.message)
                    }
                    })
            }
        }

    }

</script>
