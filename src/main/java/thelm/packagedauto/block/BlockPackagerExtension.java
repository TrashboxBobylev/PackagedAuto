package thelm.packagedauto.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thelm.packagedauto.PackagedAuto;
import thelm.packagedauto.tile.TileBase;
import thelm.packagedauto.tile.TilePackager;
import thelm.packagedauto.tile.TilePackagerExtension;

public class BlockPackagerExtension extends BlockBase {

	public static final BlockPackagerExtension INSTANCE = new BlockPackagerExtension();
	public static final Item ITEM_INSTANCE = new ItemBlock(INSTANCE).setRegistryName("packagedauto:packager_extension");
	public static final ModelResourceLocation MODEL_LOCATION = new ModelResourceLocation("packagedauto:packager_extension#normal");

	protected BlockPackagerExtension() {
		super(Material.IRON);
		setHardness(15F);
		setResistance(25F);
		setSoundType(SoundType.METAL);
		setTranslationKey("packagedauto.packager_extension");
		setRegistryName("packagedauto:packager_extension");
		setCreativeTab(PackagedAuto.CREATIVE_TAB);
	}

	@Override
	public TileBase createNewTileEntity(World worldIn, int meta) {
		return new TilePackagerExtension();
	}

	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos) {
		super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
		if (!worldIn.isRemote){
			TilePackagerExtension tile = (TilePackagerExtension) worldIn.getTileEntity(pos);
			if (tile != null){
				tile.checkRedstone();
			}
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModels() {
		ModelLoader.setCustomModelResourceLocation(ITEM_INSTANCE, 0, MODEL_LOCATION);
	}
}
