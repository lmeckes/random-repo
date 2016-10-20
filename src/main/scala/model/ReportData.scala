package model

/**
  * Created by louis on 20/10/2016.
  */
case class ReportData(
                       tenWithMost: Seq[(String, Int)],
                       tenWithLeast: Seq[(String, Int)],
                       runwaysTypesByCountry: Map[String, Map[String, Int]],
                       mostCommonRunwayIdent: Seq[(String, Int)]
                     )