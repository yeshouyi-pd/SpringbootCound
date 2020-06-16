<template>
    <div>
        <button type="button" v-on:click="selectFile()" class="btn btn-white btn-default btn-round">
            <i class="ace-icon fa fa-upload"></i>
            {{text}}
        </button>

        <input   class="hidden" type="file" ref = "file"  v-bind:id="inputId+'-input'" v-on:change="uploadFile()">

    </div>
</template>

<script>
  export default {
    name: 'file',
    props: {
      text:{
          default: "上传文件"
      },
        inputId:{
            default: "file-upload"
        },
        suffixs:{
            default: ["jpg","jpeg","png"]
        },
      afterUpload: {
        type: Function,
        default: null
      },
    },
    data: function () {
      return {
      }
    },
    methods: {
        uploadFile(){
            let _this = this;
            let formDate = new window.FormData();
            //key :file 必须前后端参数名一致
            let file = _this.$refs.file.files[0];//ref =file 别名
            //_this.$refs.file.files[0] ==document.querySelector('#file-upload-input').files[0])
            //
            //判断文件格式
            let suffixs =_this.suffixs;
            let fileName =file.name;
            let suffix = fileName.substring(fileName.lastIndexOf(".")+1,fileName.length).toLocaleLowerCase();
            let validateSuffix =false;
            for(let  i =0 ;i <suffixs.length;i++){
                if(suffixs[i].toLocaleLowerCase()===suffix){
                    validateSuffix=true;
                    break;
                }
            }
            if(! validateSuffix){
                Toast.warning("文件格式不正确！只支持上传："+suffixs.join(","));
                $("#"+_this.inputId+"-input").val("");//为防止第二次点击不出发onchang事件问题
                return;
            }

            formDate.append('file',file);
            Loading.show();
            _this.$ajax.post(process.env.VUE_APP_SERVER+'/file/admin/upload',formDate).then( (response)=> {
                Loading.hide();
                let resp = response.data;
                console.log("文件地址："+resp)
                _this.afterUpload(resp);//自定义回调函数
                $("#"+_this.inputId+"-input").val("");//为防止第二次点击不出发onchang事件问题


            });

        },
        selectFile(){
            let _this =this;
            $("#"+_this.inputId+"-input").trigger("click");

        }
    }
  }
</script>

