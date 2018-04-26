<div class="footer">
    <div class="container">
        <div class="row">
                <ul class="list-inline">
                    <li><a href="/"><h2>blog</h2></a></li>
                    <li><a href="#">关于</a></li>
                    <li><a href="#">联系我们</a></li>
                    <li>2018  <span class="glyphicon glyphicon-user"></span>  chance9077</li>
                </ul>
        </div>
    </div>
    <div class="modal fade" tabindex="-1" id="myModel">
        <div class="modal-dialog modal-sm" style="top: 300px">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                    <h5 class="modal-title">提示</h5>
                </div>
                <div class="modal-body">
                    <p id="modalBody"></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default btn-sm" data-dismiss="modal">确定</button>
                </div>
            </div>
        </div>
        <script>
            function openalert(message) {
                $('#modalBody').html(message);
                $('#myModel').modal();
            }
        </script>
    </div>
</div>