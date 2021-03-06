/**
 * FILE: QuadRectangle.java
 * PATH: org.datasyslab.geospark.spatialPartitioning.quadtree.QuadRectangle.java
 * Copyright (c) 2015-2017 GeoSpark Development Team
 * All rights reserved.
 */
package org.datasyslab.geospark.spatialPartitioning.quadtree;

import com.vividsolutions.jts.geom.Envelope;

import java.io.Serializable;

public class QuadRectangle implements Serializable{
    public double x, y, width, height;
    public Integer partitionId = -1;
    public QuadRectangle(Envelope envelope)
    {
        this.x = envelope.getMinX();
        this.y = envelope.getMinY();
        this.width = envelope.getWidth();
        this.height = envelope.getHeight();
    }
    public QuadRectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public boolean contains(int x, int y) {
        return this.width > 0 && this.height > 0
                && x >= this.x && x <= this.x + this.width
                && y >= this.y && y <= this.y + this.height;
    }

    public boolean contains(QuadRectangle r) {
        return this.width > 0 && this.height > 0 && r.width > 0 && r.height > 0
                && r.x >= this.x && r.x + r.width <= this.x + this.width
                && r.y >= this.y && r.y + r.height <= this.y + this.height;
    }
    /*
    public boolean contains(int x, int y) {
        return this.width > 0 && this.height > 0
                && x >= this.x && x <= this.x + this.width
                && y >= this.y && y <= this.y + this.height;
    }
    */

    public int getUniqueId()
    {
        /*
        Long uniqueId = Long.valueOf(-1);
        try {
            uniqueId = Long.valueOf(RasterizationUtils.Encode2DTo1DId(resolutionX,resolutionY,(int)this.x,(int)this.y));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return uniqueId;
        */
        return hashCode();
    }

    public Envelope getEnvelope()
    {
        return new Envelope(x,x+width,y,y+height);
    }
    @Override
    public String toString() {
        return "x: " + x + " y: " + y + " w: " + width + " h: " + height + " PartitionId: "+partitionId;
    }

    @Override
    public int hashCode()
    {
        String stringId = ""+x+y+width+height;
        return stringId.hashCode();
    }
}
