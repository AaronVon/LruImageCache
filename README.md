README
之前遇到 asynctask 多次调用的异常，因为 GetImageFromHTTP 只有一个实例，多次调用就会有异常抛出
现在暂时每次请求下载图片时，都新建一个 asynctask 对象去执行，但是第一次加载时，也会遇到性能低下的问题。。。