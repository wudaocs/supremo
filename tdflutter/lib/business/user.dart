import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';

class FlutterBarPage extends StatelessWidget {
  final String title;

  FlutterBarPage({Key key, this.title}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    var screenSize = MediaQuery.of(context).size;
    return Scaffold(
        appBar: PreferredSize(
            child: AppBar(
              title: Text(title),
            ),
            preferredSize: Size.fromHeight(screenSize.height * 0.10)));
  }
}

class FlutterPage extends StatelessWidget {
  final String title;

  String textToShow = "I Like Flutter";

  FlutterPage({Key key, this.title}) : super(key: key);

  void _back(BuildContext context){
    Navigator.pop(context);
  }

  @override
  Widget build(BuildContext context) {
    return WillPopScope(
        child: Stack(
          alignment: Alignment.center,
          children: <Widget>[
            Positioned(
              top: 18.0,
              child: Listener(
                child: CircleAvatar(
                    backgroundImage: NetworkImage(
                      "https://images.liqucn.com/img/h1/h994/img201802021024070_info300X300.jpg",
                    ),
                    radius: 50.0),
                onPointerDown: (event) => print("down0"),
              ),
            ),
            Positioned(
              top: 160.0,
              child: GestureDetector(
                onTap: () => _back(context),
                child: CircleAvatar(
                    backgroundImage: CachedNetworkImageProvider(
                        "https://images.liqucn.com/img/h1/h994/img201802021024070_info300X300.jpg"),
                    radius: 50.0),
              ),
            ),
            Positioned(
              right: 18.0,
              child: CachedNetworkImage(
                  imageUrl:
                      "https://images.liqucn.com/img/h1/h994/img201802021024070_info300X300.jpg",
                  placeholder: (context, url) => CircularProgressIndicator(),
                  errorWidget: (context, url, error) => Icon(Icons.error)),
            ),
            Positioned(
              bottom: 18.0,
              child: Container(child: Text("xxxxxxsss")),
            ),
            Positioned(
              left: 18.0,
              child: Text("left"),
            )
          ],
        ),
        onWillPop: () async {
          // Navigator.pop(context);
          return false;
        });
  }
}
